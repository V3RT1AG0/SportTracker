package com.example.sporttracker.model

import io.reactivex.Single
import retrofit2.http.GET

import retrofit2.http.Query


//@GET("users/{user}/repos")
//fun listRepos(@Path("user") user: String): Call<List<Repo>>

interface Routes {
    @GET("eventslast.php")
    fun getHistory(@Query("id") id: Int): Single<History>

    @GET("searchteams.php")
    fun getTeams(@Query("t") query:String): Single<Teams>

    @GET("lookupteam.php")
    fun getTeamDetails(@Query("id") id:Int): Single<Teams>
}
