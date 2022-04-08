package com.android.carol.sportleagues.data.remote.dtoMatches


import com.squareup.moshi.Json

data class CountryX(
    @Json(name = "continent")
    val continent: String,
    @Json(name = "country_code")
    val countryCode: String,
    @Json(name = "country_id")
    val countryId: Int,
    @Json(name = "name")
    val name: String
)