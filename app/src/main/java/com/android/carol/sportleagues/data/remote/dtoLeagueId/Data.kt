package com.android.carol.sportleagues.data.remote.dtoLeagueId


import com.squareup.moshi.Json

data class Data(
    @Json(name = "country_id")
    val countryId: Int,
    @Json(name = "league_id")
    val leagueId: Int,
    @Json(name = "name")
    val name: String
)