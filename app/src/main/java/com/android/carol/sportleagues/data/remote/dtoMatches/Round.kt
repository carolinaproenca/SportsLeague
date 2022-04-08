package com.android.carol.sportleagues.data.remote.dtoMatches


import com.squareup.moshi.Json

data class Round(
    @Json(name = "is_current")
    val isCurrent: Any,
    @Json(name = "name")
    val name: String,
    @Json(name = "round_id")
    val roundId: Int
)