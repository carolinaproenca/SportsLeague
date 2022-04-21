package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.repositories.LeagueRepository
import java.lang.Exception

class FakeLeagueRepository : LeagueRepository {

    lateinit var league : League
    private val leagues = mutableListOf<LeagueResp>()
    private val leaguesList = mutableListOf<League>()

    private var shouldReturnSuccess = false

    fun setReturnSuccess(value : Boolean){
        shouldReturnSuccess = value
    }

    override suspend fun getLeagueById(id : Int): League {
        league = League(123,456,"League1")
        return league
    }

    override suspend fun getLeagueId(id : Int) : Boolean{
        league = League(123,345,"League")
        return if(shouldReturnSuccess){
            Success(id)
            true
        }else {
            Error(Exception("League Test Exception"))
            false
        }
    }

    override fun getLeague(): List<League> {
        return leaguesList
    }

    fun insertLeagues(league: LeagueResp){
        leagues.add(league)
    }

}