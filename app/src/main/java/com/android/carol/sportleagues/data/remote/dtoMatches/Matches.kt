package com.android.carol.sportleagues.data.remote.dtoMatches


import com.squareup.moshi.Json

data class Matches(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "query")
    val query: Query
)