package com.example.catfacts.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.net.UnknownHostException


@Suppress("PropertyName","SpellCheckingInspection")
open class BaseViewModel<T : BaseState<T>>(standardState: T) : ViewModel(), KoinComponent {

    protected val TAG = standardState.tag

    protected val _state = MutableStateFlow(standardState)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        when(exception) {
            is UnknownHostException -> onNetworkError()
            else -> onError()
        }
    }
    protected fun onNetworkError() {
        _state.update { it.onNetworkError() }
    }

    protected fun onError() {
        _state.update { it.onError() }
    }

    protected fun launchIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(
            context = handler + Dispatchers.IO,
            block = block
        )
    }
}