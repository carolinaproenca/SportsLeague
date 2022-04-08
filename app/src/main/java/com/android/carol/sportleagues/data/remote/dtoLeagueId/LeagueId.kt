package com.android.carol.sportleagues.data.remote.dtoLeagueId


import com.squareup.moshi.Json

data class LeagueId(
    @Json(name = "data")
    val data: List<Data>,
    @Json(name = "query")
    val query: Query
)