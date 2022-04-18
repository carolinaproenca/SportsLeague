package com.android.carol.sportleagues.data.remote.models


import com.android.carol.sportleagues.data.remote.dtoSeasons.Data
import com.android.carol.sportleagues.data.remote.dtoSeasons.Query
import com.squareup.moshi.Json

data class SeasonsResponse(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "query")
    val query: Query
)