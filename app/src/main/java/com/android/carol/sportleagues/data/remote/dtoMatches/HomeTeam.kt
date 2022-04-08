package com.android.carol.sportleagues.data.remote.dtoMatches


import com.squareup.moshi.Json

data class HomeTeam(
    @Json(name = "common_name")
    val commonName: String,
    @Json(name = "country")
    val country: CountryX,
    @Json(name = "logo")
    val logo: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "short_code")
    val shortCode: String,
    @Json(name = "team_id")
    val teamId: Int
)