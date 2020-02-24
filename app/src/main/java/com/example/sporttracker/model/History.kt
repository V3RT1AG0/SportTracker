package com.example.sporttracker.model

import com.google.gson.annotations.SerializedName


data class Event(
    @SerializedName("strEvent")
    val event_name: String?,

    @SerializedName("intHomeScore")
    val home_score: Int,

    @SerializedName("intAwayScore")
    val away_score: Int,

    @SerializedName("strHomeTeam")
    val home_team: String?,

    @SerializedName("strAwayTeam")
    val away_team: String?,

    @SerializedName("strLeague")
    val league_name: String?,

    @SerializedName("dateEvent")
    val date: String?

)

data class History(
    @SerializedName("results")
    val events: List<Event>?
)

