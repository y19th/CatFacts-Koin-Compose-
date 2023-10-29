package com.example.catfacts.domain.viewmodels

import android.util.Log
import com.example.catfacts.data.models.toFactsList
import com.example.catfacts.domain.events.FactsEvents
import com.example.catfacts.domain.models.Facts
import com.example.catfacts.domain.models.ListModel
import com.example.catfacts.domain.repository.FactsRepository
import com.example.catfacts.domain.states.FactsState
import com.example.catfacts.util.BaseViewModel
import kotlinx.coroutines.flow.update

class FactsViewModel : BaseViewModel<FactsState>(FactsState()) {

    private val repository = FactsRepository()


    init {
        launchIO {
            fetchFacts()
            fetchSingleFact()
        }
    }

    fun onEvent(event: FactsEvents) {
        when(event) {
            is FactsEvents.OnShowMore -> {
                with(state.value.listModel) {
                    val nextPage = currentPage.plus(1)
                    if(nextPage == lastPage) {
                        _state.update { it.copy(isLastPage = true) }
                    }
                    fetchPage(nextPage)
                }
            }
        }
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
                onResult = { listModel ->
                    Log.i(TAG,"response is $listModel")
                    if(listModel != null) {
                        _state.update {
                            it.copy(
                                listModel = listModel.toListModel()
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

    private fun fetchPage(page: Int) {
        launchIO {
            repository.fetchPage(
                page = page,
                onResult = { nextPage ->
                    Log.i(TAG,"next page response is $nextPage")
                    if(nextPage != null) {
                        val newList = concatLists(nextPage.factsList.toFactsList())
                        _state.update {
                            it.copy(
                                listModel = ListModel(
                                    currentPage = nextPage.currentPage,
                                    factsList = newList,
                                    firstPageUrl = nextPage.firstPageUrl,
                                    lastPageUrl = nextPage.lastPageUrl,
                                    from = nextPage.from,
                                    lastPage = nextPage.lastPage,
                                    nextPageUrl = nextPage.nextPageUrl ?: "",
                                    total = nextPage.total
                                )
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

    private fun concatLists(newList: List<Facts>): List<Facts> {
        val list = state.value.listModel.factsList.toMutableList()
        newList.forEach {
            list.add(it)
        }
        return list
    }
}