package com.smorzhok.coroutineflow.lesson4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class CryptoViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            repository.loadData()
        }
    }

    fun refreshList(){
        viewModelScope.launch {
            repository.loadData()
        }
    }

    private val repository = CryptoRepository

    val state: Flow<State> = repository.currencyListFlow
        .filter { it.isNotEmpty() }
        .map { State.Content(it) as State }
        .onStart {
            emit(State.Loading)
        }
}