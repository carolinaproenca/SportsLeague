package com.android.carol.sportleagues.data.remote.dtoTeams


import com.squareup.moshi.Json

data class Data(
    @Json(name = "common_name")
    val commonName: String?,
    @Json(name = "country")
    val country: Country,
    @Json(name = "logo")
    val logo: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "short_code")
    val shortCode: String,
    @Json(name = "team_id")
    val teamId: Int
)