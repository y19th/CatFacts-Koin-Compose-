package com.example.catfacts.domain.repository

import com.example.catfacts.data.api.CatFactsApi
import com.example.catfacts.data.models.ResponseFactModel
import com.example.catfacts.data.models.ResponseListModel
import com.example.catfacts.di.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactsRepository {

    private val instance = RetrofitBuilder().instance(CatFactsApi::class.java)

    fun fetchFacts(
        onResult: (ResponseListModel?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        instance.fetchFacts().enqueue(
            object: Callback<ResponseListModel> {
                override fun onResponse(
                    call: Call<ResponseListModel>,
                    response: Response<ResponseListModel>
                ) {
                    onResult.invoke(response.body())
                }

                override fun onFailure(call: Call<ResponseListModel>, t: Throwable) {
                    onError.invoke(t)
                }

            }
        )
    }

    fun fetchSingleFact(
        onResult: (ResponseFactModel?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        instance.fetchSingleFact().enqueue(
            object : Callback<ResponseFactModel> {
                override fun onResponse(
                    call: Call<ResponseFactModel>,
                    response: Response<ResponseFactModel>
                ) {
                    onResult.invoke(response.body())
                }

                override fun onFailure(call: Call<ResponseFactModel>, t: Throwable) {
                    onError.invoke(t)
                }

            }
        )
    }

    fun fetchPage(
        page: Int,
        onResult: (ResponseListModel?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        instance.fetchPage(page).enqueue(
            object : Callback<ResponseListModel> {
                override fun onResponse(
                    call: Call<ResponseListModel>,
                    response: Response<ResponseListModel>
                ) {
                    onResult.invoke(response.body())
                }

                override fun onFailure(call: Call<ResponseListModel>, t: Throwable) {
                    onError.invoke(t)
                }

            }
        )
    }
}