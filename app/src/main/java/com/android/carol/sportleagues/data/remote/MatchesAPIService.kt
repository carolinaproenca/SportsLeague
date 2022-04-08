package com.android.carol.sportleagues.data.remote

import com.android.carol.sportleagues.common.Constants.API
import com.android.carol.sportleagues.common.Constants.URL
import com.android.carol.sportleagues.data.remote.dtoMatches.Matches
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://app.sportdataapi.com/api/v1/soccer/matches?apikey=74915aa0-b43a-11ec-b80b-bd2ed61fcd18&season_id=762


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitMatches = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface MatchesAPIService {
    @GET("matches")
    suspend fun getProperties(
        @Query("apikey") key : String = API, @Query("season_id") season_id : Int
    ): Matches
}

object MatchesAPI{
    val retrofitServiceMatch : MatchesAPIService by lazy{
        retrofitMatches.create(MatchesAPIService::class.java)
    }
}


