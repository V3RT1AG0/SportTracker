package com.example.showtracker.model

import com.example.sporttracker.model.History
import com.example.sporttracker.model.Teams
import io.reactivex.Single
import retrofit2.http.GET

import retrofit2.http.Query

interface Routes {
    @GET("eventslast.php")
    fun getHistory(@Query("id") id: Int): Single<History>

    @GET("searchteams.php")
    fun getTeams(@Query("t") query:String): Single<Teams>

    @GET("lookupteam.php")
    fun getTeamDetails(@Query("id") id:Int): Single<Teams>
}
