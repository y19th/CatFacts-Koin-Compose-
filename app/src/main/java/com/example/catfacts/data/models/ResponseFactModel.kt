package com.example.catfacts.data.models

import android.health.connect.datatypes.units.Length
import com.example.catfacts.domain.models.Facts
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

/* cant use it cause free api is down
data class ResponseFactModel(
    @SerializedName("_id") val id: Int = -1,
    @SerializedName("_v") val num: Int = -1,
    @SerializedName("user") val userId: Int = -1,
    @SerializedName("text") val fact: String = "",
    @SerializedName("updatedAt") val updatedAt: Timestamp = Timestamp(-1),
    @SerializedName("sendDate") val sendDate: Timestamp = Timestamp(-1),
    @SerializedName("deleted") val isDeleted: Boolean = false,
    @SerializedName("source") val source: String = "",
    @SerializedName("type") val animalType: String = ""
) {
    fun toFacts() = Facts (
        id,num,userId, fact, updatedAt, sendDate, isDeleted, source, animalType
    )
}

fun List<ResponseFactModel>.toFactsList(): List<Facts> {
    val newList = mutableListOf<Facts>()
    for(i in this) newList.add(i.toFacts())
    return newList
}
*/

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