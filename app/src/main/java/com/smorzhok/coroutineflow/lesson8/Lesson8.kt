package com.smorzhok.coroutineflow.lesson8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


val scope = CoroutineScope(Dispatchers.IO)
suspend fun main() {
    val flow = getFlow()

    val job1 = scope.launch {
        flow.first().let {
            println(it)
        }
    }

    val job2 = scope.launch {
        flow.collect { println(it) }
    }
    job1.join()
    job2.join()

}

fun getFlow() = flow {
    repeat(1000) {
        println("Emmited $it")
        emit(it)
        delay(1000)
    }
}