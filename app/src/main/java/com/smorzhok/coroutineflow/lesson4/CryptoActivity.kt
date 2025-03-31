package com.smorzhok.coroutineflow.lesson4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.smorzhok.coroutineflow.databinding.ActivityCryptoBinding
import kotlinx.coroutines.launch

class CryptoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCryptoBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[CryptoViewModel::class.java]
    }

    private val adapter = CryptoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
        binding.refreshButton.setOnClickListener {
            viewModel.refreshList()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCurrencyPriceList.adapter = adapter
        binding.recyclerViewCurrencyPriceList.itemAnimator = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.state.collect {
                    when (it) {
                        is State.Initial -> {
                            binding.progressBarLoading.isVisible = false
                            binding.refreshButton.isEnabled = false
                        }
                        is State.Loading -> {
                             binding.progressBarLoading.isVisible = true
                            binding.refreshButton.isEnabled = false
                        }
                        is State.Content -> {
                            binding.progressBarLoading.isVisible = false
                            binding.refreshButton.isEnabled = true
                            adapter.submitList(it.currencyList)
                        }
                    }
                }
            }

        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, CryptoActivity::class.java)
    }
}