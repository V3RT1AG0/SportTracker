package com.example.showtracker.model


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiService{

    private val BASE_URL = "https://thesportsdb.com/api/v1/json/1/"

    fun getSportsApi(): Routes {
        val sports_routes = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Routes::class.java)
        return sports_routes
    }
}