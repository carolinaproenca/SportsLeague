package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.repositories.TeamsRepository
import java.lang.Exception

class FakeTeamsRepository : TeamsRepository {

    lateinit var teams : Teams
    private val team = mutableListOf<TeamsResp>()
    private val teamsList = mutableListOf<Teams>()

    private var shouldReturnSuccess = false

    fun setReturnSuccess(value : Boolean){
        shouldReturnSuccess = value
    }


    override suspend fun getTeamsByCountryId(countryId: Int): Teams {
        teams = Teams("Logo1", "Team1")
        return teams
    }

    override suspend fun getTeamsCountryId(id : Int) : Boolean{
        teams = Teams("Logo1", "Team1")
        return if(shouldReturnSuccess){
            Success(id)
            true
        }else {
            Error(Exception("Season Test Exception"))
            false
        }
    }

    override fun getTeam(): List<Teams> {
        return teamsList
    }

    fun insertTeam(teamResp : TeamsResp){
        team.add(teamResp)
    }

}