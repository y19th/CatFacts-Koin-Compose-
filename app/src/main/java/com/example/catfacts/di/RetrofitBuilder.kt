package com.example.catfacts.di

import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://catfact.ninja/"

class RetrofitBuilder : KoinComponent {


    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> instance(service: Class<T>): T {
        return retrofitBuilder.create(service)
    }

}