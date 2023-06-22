package com.example.externalapiwithretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    var apiInterface :ApiInterface
    init {
        val api = "https://jsonplaceholder.typicode.com"

        val retrofit = Retrofit.Builder()
            .baseUrl(api)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

}