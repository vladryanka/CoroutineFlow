package com.smorzhok.coroutineflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smorzhok.coroutineflow.databinding.ActivityMainBinding
import com.smorzhok.coroutineflow.lesson2.UsersActivity
import com.smorzhok.coroutineflow.lesson4.CryptoActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonUsersActivity.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }
        binding.buttonCryptoActivity.setOnClickListener {
            startActivity(CryptoActivity.newIntent(this))
        }
    }
}