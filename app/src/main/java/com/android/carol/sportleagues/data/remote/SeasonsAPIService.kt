package com.android.carol.sportleagues.data.remote

import com.android.carol.sportleagues.common.Constants.API
import com.android.carol.sportleagues.common.Constants.URL
import com.android.carol.sportleagues.data.remote.dtoSeasons.Seasons
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//league
//https://app.sportdataapi.com/api/v1/soccer/seasons?apikey=74915aa0-b43a-11ec-b80b-bd2ed61fcd18&league_id=490

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitSeasons = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface SeasonsAPIService {
    @GET("seasons")
    suspend fun getProperties(
        @Query("apikey") key:String = API, @Query("league_id") league_id : Int
    ): Seasons
}

object SeasonsAPI{
    val retrofitServiceSeason : SeasonsAPIService by lazy{
        retrofitSeasons.create(SeasonsAPIService::class.java)
    }
}
