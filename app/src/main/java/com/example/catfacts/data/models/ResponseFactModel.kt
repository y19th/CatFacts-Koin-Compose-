package com.example.catfacts.data.models

import com.example.catfacts.domain.models.Facts
import com.google.gson.annotations.SerializedName


data class ResponseFactModel(
    @SerializedName("fact") val fact: String = "",
    @SerializedName("length") val length: Int = -1
) {
    fun toFacts() = Facts(fact, length)
}


fun List<ResponseFactModel>.toFactsList() : List<Facts> {
    val newList = mutableListOf<Facts>()
    for(item in this) newList.add(item.toFacts())
    return newList
}