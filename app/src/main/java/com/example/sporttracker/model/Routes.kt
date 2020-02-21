package com.example.showtracker.model

import com.example.sporttracker.model.History
import io.reactivex.Single
import retrofit2.http.GET

import retrofit2.http.Query

interface Routes {
    @GET("eventslast.php")
    fun getHistory(@Query("id") id: Int): Single<History>
}
