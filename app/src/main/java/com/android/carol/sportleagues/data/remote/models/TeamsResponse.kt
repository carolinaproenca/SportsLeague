package com.android.carol.sportleagues.data.remote.models


import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.data.remote.dtoTeams.Query
import com.squareup.moshi.Json

data class TeamsResponse(
    @Json(name = "data")
    val data: List<Data>,
    @Json(name = "query")
    val query: Query
)