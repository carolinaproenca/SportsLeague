package com.android.carol.sportleagues.data.repositories

import android.util.Log
import com.android.carol.sportleagues.common.Constants.API
import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueId
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.data.remote.dtoLeagueId.Query
import com.android.carol.sportleagues.domain.repositories.DLeagueIdRepository
import java.io.IOException

class FakeLeagueIdRepository : DLeagueIdRepository {

    private lateinit var league : LeagueId
    private val leagues = mutableListOf<LeagueResp>()
    private val data = mutableListOf<Data>()
    private val query = Query(API,"true")

    override suspend fun getProp(subscribed: Boolean): LeagueId {
        league = LeagueId(data, query)
        return if(subscribed)
            league
        else{
            LeagueId(data,query)
        }
    }

    fun insertLeagues(league: LeagueResp){
        leagues.add(league)
    }

}