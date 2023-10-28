package com.example.catfacts.domain.viewmodels

import android.util.Log
import com.example.catfacts.data.models.toFactsList
import com.example.catfacts.domain.repository.FactsRepository
import com.example.catfacts.domain.states.FactsState
import com.example.catfacts.util.BaseViewModel
import kotlinx.coroutines.flow.update

class FactsViewModel : BaseViewModel<FactsState>(FactsState()) {

    private val repository = FactsRepository()


    init {
        //fetchFacts()
        fetchSingleFact()
    }


    private fun fetchSingleFact() {
        launchIO {
            repository.fetchSingleFact(
                onResult = { fact ->
                    Log.i(TAG,"single fact is $fact")
                    if(fact != null) {
                        _state.update {
                            it.copy(factsList = listOf(fact.toFacts()))
                        }
                    }
                },
                onError = {
                    Log.i(TAG,"onError throwed $it")
                    onError()
                }
            )
        }
    }


    private fun fetchFacts() {
        launchIO {
            repository.fetchFacts(
                onResult = { list ->
                    Log.i(TAG,"response is $list")
                    if(!list.isNullOrEmpty()) {
                        _state.update {
                            it.copy(
                                factsList = list.toFactsList()
                            )
                        }
                    }
                },
                onError = {
                    Log.i(TAG,"onError throwed $it")
                    onError()
                }
            )
        }
    }
}