package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.repositories.TeamsRepository

class FakeTeamsRepository : TeamsRepository {

    private lateinit var teams : Teams
    private val team = mutableListOf<TeamsResp>()

    override suspend fun getTeamsByCountryId(countryId: Int): Teams {
        return teams
    }

    fun insertTeam(teamResp : TeamsResp){
        team.add(teamResp)
    }


}