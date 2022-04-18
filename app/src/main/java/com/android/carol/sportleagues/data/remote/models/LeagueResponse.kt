package com.android.carol.sportleagues.data.remote.models
import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data
import com.android.carol.sportleagues.data.remote.dtoLeagueId.Query
import com.squareup.moshi.Json

data class LeagueResponse(
    @Json(name = "data")
    val data: List<Data>,
    @Json(name = "query")
    val query: Query
)