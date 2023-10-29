package com.example.catfacts.data.api

import com.example.catfacts.data.models.ResponseFactModel
import com.example.catfacts.data.models.ResponseListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactsApi {

    @GET("facts/")
    fun fetchFacts() : Call<ResponseListModel>

    @GET("facts/")
    fun fetchPage(
        @Query("page") page: Int
    ) : Call<ResponseListModel>

    @GET("fact/")
    fun fetchSingleFact() : Call<ResponseFactModel>
}