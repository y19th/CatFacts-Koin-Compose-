package com.example.catfacts.domain.states

import com.example.catfacts.domain.models.Facts
import com.example.catfacts.domain.models.ListModel
import com.example.catfacts.util.BaseState

data class FactsState(

    val factsList: List<Facts> = emptyList(),
    val listModel: ListModel = ListModel(),

    val isLastPage: Boolean = false,

    val isError: Boolean = false,
    val isNetworkError: Boolean = false

) : BaseState<FactsState> {
    override val tag: String
        get() = "FactsState"

    override fun onError(): FactsState {
        return this.copy(isError = true)
    }

    override fun onNetworkError(): FactsState {
        return this.copy(isNetworkError = true)
    }

}
