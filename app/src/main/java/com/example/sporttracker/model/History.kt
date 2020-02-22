package com.example.sporttracker.model

import com.google.gson.annotations.SerializedName


data class Event(
    @SerializedName("strEvent")
    val event_name: String?,
    @SerializedName("intHomeScore")
    val home_score: String?,
    @SerializedName("intAwayScore")
    val away_score: String?,
    @SerializedName("strLeague")
    val league_name: String?
)

data class History(
    @SerializedName("results")
    val events: List<Event>
)

