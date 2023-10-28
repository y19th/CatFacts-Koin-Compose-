package com.example.catfacts.util

interface BaseState<T> {
    val tag: String

    fun onError() : T

    fun onNetworkError() : T
}