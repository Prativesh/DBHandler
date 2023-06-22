package com.example.externalapiwithretrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/photos")
    fun  getUsers(): Call<List<UserModel>>

}