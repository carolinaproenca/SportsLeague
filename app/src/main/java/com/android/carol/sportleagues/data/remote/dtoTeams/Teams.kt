package com.android.carol.sportleagues.data.remote.dtoTeams


import com.squareup.moshi.Json

data class Teams(
    @Json(name = "data")
    val data: List<Data>,
    @Json(name = "query")
    val query: Query
)