package com.android.carol.sportleagues.domain.use_case.teams

import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.domain.model.TeamsProp
import com.android.carol.sportleagues.domain.repositories.DTeamsRepository

class GetTeamsUseCase constructor(private val repository: DTeamsRepository) {

    suspend fun getProp(country_id : Int) = repository.getProp(country_id)

    private val teams = mutableListOf<TeamsProp>()
    private val arrayTeams = arrayListOf<Data>()

    fun getTeam(team: List<Data>) : List<TeamsProp>{
        for(i in team.indices){
            arrayTeams.add(team[i])
            teams.add(TeamsProp(team[i].logo, team[i].name))
        }
        return teams
    }
}