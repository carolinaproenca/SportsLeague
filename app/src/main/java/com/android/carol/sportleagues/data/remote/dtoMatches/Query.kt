package com.android.carol.sportleagues.data.remote.dtoMatches


import com.squareup.moshi.Json

data class Query(
    @Json(name = "apikey")
    val apikey: String,
    @Json(name = "season_id")
    val seasonId: String
)