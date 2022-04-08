package com.android.carol.sportleagues.domain.use_case.teams

import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.data.remote.dtoTeams.Teams
import com.android.carol.sportleagues.domain.model.TeamsProp

class GetTeamsUseCase /*constructor(private val repository: TeamsRepository)*/ {

   // suspend fun getProp() = repository.getProp()

    private val teams = mutableListOf<TeamsProp>()
    private val arrayTeams = arrayListOf<Teams>()

    fun getTeam(team: List<Teams>) : List<TeamsProp>{
        for(i in team.indices){
            arrayTeams.add(team[i])
            teams.add(TeamsProp(team[i].data[i].logo, team[i].data[i].name))
        }
        return teams
    }
}