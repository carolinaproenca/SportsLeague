package com.android.carol.sportleagues.data.remote.dtoMatches


import com.squareup.moshi.Json

data class Venue(
    @Json(name = "capacity")
    val capacity: Int,
    @Json(name = "city")
    val city: String,
    @Json(name = "country_id")
    val countryId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "venue_id")
    val venueId: Int
)