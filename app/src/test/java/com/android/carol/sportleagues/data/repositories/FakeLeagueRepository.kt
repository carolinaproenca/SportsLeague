package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.repositories.LeagueRepository

class FakeLeagueRepository : LeagueRepository {

    lateinit var league : League
    private val leagues = mutableListOf<LeagueResp>()

    override suspend fun getLeagueById(id : Int): League {
        return league
    }

    fun insertLeagues(league: LeagueResp){
        leagues.add(league)
    }

}