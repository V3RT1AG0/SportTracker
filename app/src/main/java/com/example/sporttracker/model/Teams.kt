package com.example.sporttracker.model

import com.google.gson.annotations.SerializedName


data class Team(
    @SerializedName("strTeam")
    val team_name: String?,
    @SerializedName("idTeam")
    val team_id: Int?,
    @SerializedName("strTeamBadge")
    val logo: String?,
    @SerializedName("strSport")
    val sport: String?,
    @SerializedName("strFacebook")
    val facebook: String?,
    @SerializedName("strTwitter")
    val twitter: String?,
    @SerializedName("strInstagram")
    val instagram: String?,
    @SerializedName("strAlternate")
    val alternate: String?


)

data class Teams(
    @SerializedName("teams")
    val teams: List<Team>
)

