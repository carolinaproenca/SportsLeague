package com.android.carol.sportleagues.data.remote.dtoTeams


import com.squareup.moshi.Json

data class Query(
    @Json(name = "apikey")
    val apikey: String,
    @Json(name = "country_id")
    val countryId: String
)