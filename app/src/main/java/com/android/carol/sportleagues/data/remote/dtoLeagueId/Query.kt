package com.android.carol.sportleagues.data.remote.dtoLeagueId


import com.squareup.moshi.Json

data class Query(
    @Json(name = "apikey")
    val apikey: String,
    @Json(name = "subscribed")
    val subscribed: String
)