package com.example.catfacts.domain.repository

import com.example.catfacts.data.api.CatFactsApi
import com.example.catfacts.data.models.ResponseFactModel
import com.example.catfacts.di.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactsRepository {

    private val instance = RetrofitBuilder().instance(CatFactsApi::class.java)

    fun fetchFacts(
        onResult: (List<ResponseFactModel>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        instance.fetchFacts().enqueue(
            object: Callback<List<ResponseFactModel>> {
                override fun onResponse(
                    call: Call<List<ResponseFactModel>>,
                    response: Response<List<ResponseFactModel>>
                ) {
                    onResult.invoke(response.body())
                }

                override fun onFailure(call: Call<List<ResponseFactModel>>, t: Throwable) {
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
}