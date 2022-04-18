package com.android.carol.sportleagues.data.remote.models


import com.android.carol.sportleagues.data.remote.dtoMatches.Data
import com.android.carol.sportleagues.data.remote.dtoMatches.Query
import com.squareup.moshi.Json

data class MatchesResponse(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "query")
    val query: Query
)