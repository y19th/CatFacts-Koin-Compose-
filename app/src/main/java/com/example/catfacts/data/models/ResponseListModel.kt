package com.example.catfacts.data.models

import com.example.catfacts.domain.models.ListModel
import com.google.gson.annotations.SerializedName

data class ResponseListModel(
    @SerializedName("current_page") val currentPage: Int = -1,
    @SerializedName("data") val factsList: List<ResponseFactModel> = emptyList(),
    @SerializedName("first_page_url") val firstPageUrl: String = "",
    @SerializedName("last_page_url") val lastPageUrl: String = "",
    @SerializedName("from") val from: Int = -1,
    @SerializedName("last_page") val lastPage: Int = -1,
    @SerializedName("next_page_url") val nextPageUrl: String? = null,
    @SerializedName("total") val total: Int = -1
) {
    fun toListModel() = ListModel(
        currentPage, factsList.toFactsList(), firstPageUrl, lastPageUrl, from, lastPage, nextPageUrl ?: "", total
    )
}

