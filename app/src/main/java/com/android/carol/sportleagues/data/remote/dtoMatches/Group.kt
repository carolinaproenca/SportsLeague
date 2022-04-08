package com.android.carol.sportleagues.data.remote.dtoMatches


import com.squareup.moshi.Json

data class Group(
    @Json(name = "group_id")
    val groupId: Int,
    @Json(name = "group_name")
    val groupName: String
)