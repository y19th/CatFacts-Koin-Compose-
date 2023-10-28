package com.example.catfacts.domain.models

import java.sql.Timestamp

/*data class Facts(
    val id: Int = -1,
    val num: Int = -1,
    val userId: Int = -1,
    val fact: String = "",
    val updatedAt: Timestamp = Timestamp(-1),
    val sendDate: Timestamp = Timestamp(-1),
    val isDeleted: Boolean = false,
    val source: String = "",
    val animalType: String = ""
)*/


data class Facts(
    val fact: String = "",
    val length: Int = -1
)