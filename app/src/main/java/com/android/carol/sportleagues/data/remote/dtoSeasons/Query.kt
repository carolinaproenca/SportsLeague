package com.android.carol.sportleagues.data.remote.dtoSeasons


import com.squareup.moshi.Json

data class Query(
    @Json(name = "apikey")
    val apikey: String,
    @Json(name = "league_id")
    val leagueId: String
)