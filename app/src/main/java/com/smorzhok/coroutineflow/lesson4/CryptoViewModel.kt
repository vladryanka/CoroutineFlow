package com.smorzhok.coroutineflow.lesson4

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    val state: Flow<State> = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(it) as State }
        .onStart {
            emit(State.Loading)
        }
}