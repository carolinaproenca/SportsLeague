package com.android.carol.sportleagues.data.remote

import com.android.carol.sportleagues.common.Constants.API
import com.android.carol.sportleagues.common.Constants.URL
import com.android.carol.sportleagues.data.remote.models.TeamsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://app.sportdataapi.com/api/v1/soccer/teams?apikey=74915aa0-b43a-11ec-b80b-bd2ed61fcd18&country_id=98

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitTeams = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface TeamsAPIService {
    @GET("teams")
    suspend fun getProperties(
        @Query("apikey") key : String = API, @Query("country_id") country_id :Int
    ): TeamsResponse
}

object TeamsAPI{
    val retrofitServiceTeams : TeamsAPIService by lazy{
        retrofitTeams.create(TeamsAPIService::class.java)
    }
}