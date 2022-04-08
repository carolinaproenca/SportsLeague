package com.android.carol.sportleagues.data.remote

import com.android.carol.sportleagues.common.Constants.API
import com.android.carol.sportleagues.common.Constants.URL
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueId
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://app.sportdataapi.com/api/v1/soccer/leagues?apikey=74915aa0-b43a-11ec-b80b-bd2ed61fcd18&subscribed=true

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitLeague = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface LeagueIdAPIService {
    @GET("leagues")
    suspend fun getProperties(
        @Query("apikey") key:String = API, @Query("subscribed") subscribed : Boolean
    ): LeagueId
}

object LeagueIdApi{
    val retrofitServiceLeague : LeagueIdAPIService by lazy{
        retrofitLeague.create(LeagueIdAPIService::class.java)
    }
}
