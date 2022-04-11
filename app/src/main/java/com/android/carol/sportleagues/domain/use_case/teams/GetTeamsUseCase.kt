package com.android.carol.sportleagues.domain.use_case.teams

import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.data.repositories.TeamsRepository
import com.android.carol.sportleagues.domain.model.TeamsProp

class GetTeamsUseCase constructor(private val repository: TeamsRepository) {

    suspend fun getProp() = repository.getProp()

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