package com.example.myapplication.experimental

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MyViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun doSomething() {
        //viewModelScope is an extension property from ViewModel, of type CoroutineScope,
        // thus a custom Coroutine Scope
        val job1 = viewModelScope.launch(context = Dispatchers.IO) {
            supervisorScope {
                launch {  }
            }
        }

        job1.cancel()

        val job2 = viewModelScope.async {
            withContext(Dispatchers.IO) {

            }
            withContext(Dispatchers.Main) {

            }
        }

        launch {
            val result = job2.await()
        }

        //able to call only launch because this class extends from CoroutineScope and defines a
        // coroutineContext
        launch(Dispatchers.IO) {

        }

    }

    private suspend fun supervisor() {
        supervisorScope {
            launch {  }
            launch {  }
        }
    }


}