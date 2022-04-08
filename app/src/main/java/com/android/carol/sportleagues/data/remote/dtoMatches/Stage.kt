package com.android.carol.sportleagues.data.remote.dtoMatches


import com.squareup.moshi.Json

data class Stage(
    @Json(name = "name")
    val name: String,
    @Json(name = "stage_id")
    val stageId: Int
)