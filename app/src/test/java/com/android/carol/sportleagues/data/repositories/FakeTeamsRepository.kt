package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.repositories.TeamsRepository
import java.lang.Exception

class FakeTeamsRepository : TeamsRepository {

    lateinit var teams : Teams
    private val team = mutableListOf<TeamsResp>()

    private var shouldReturnError = false

    fun setReturnError(value : Boolean){
        shouldReturnError = value
    }


    override suspend fun getTeamsByCountryId(countryId: Int): Teams {
        teams = Teams("Logo1", "Team1")
        return teams
    }

    override suspend fun getTeamsCountryId(id : Int) : Boolean{
        teams = Teams("Logo1", "Team1")
        return if(shouldReturnError){
            Result.Success(id)
            true
        }else {
            Error(Exception("Season Test Exception"))
            false
        }
    }

    fun insertTeam(teamResp : TeamsResp){
        team.add(teamResp)
    }

}