package com.android.carol.sportleagues.data.remote.dtoSeasons


import com.squareup.moshi.Json

data class Seasons(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "query")
    val query: Query
)