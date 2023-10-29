package com.example.catfacts.domain.models


data class ListModel (
    val currentPage: Int = -1,
    val factsList: List<Facts> = emptyList(),
    val firstPageUrl: String = "",
    val lastPageUrl: String = "",
    val from: Int = -1,
    val lastPage: Int = -1,
    val nextPageUrl: String = "",
    val total: Int = -1
)