package com.example.catfacts.data.api

import com.example.catfacts.data.models.ResponseFactModel
import retrofit2.Call
import retrofit2.http.GET

interface CatFactsApi {

    @GET("facts/")
    fun fetchFacts() : Call<List<ResponseFactModel>>

    @GET("fact/")
    fun fetchSingleFact() : Call<ResponseFactModel>
}