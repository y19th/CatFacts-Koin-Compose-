package com.example.catfacts.domain.events

sealed interface FactsEvents {
    data object OnShowMore: FactsEvents
}