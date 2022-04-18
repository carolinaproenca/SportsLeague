package com.android.carol.sportleagues.domain.use_case.teams

import com.android.carol.sportleagues.common.Constants.API
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.data.remote.dtoTeams.Query
import com.android.carol.sportleagues.data.remote.models.TeamsResponse
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.repositories.TeamsRepository

class GetTeamsUseCase constructor(private val repository: TeamsRepository) {

    suspend fun getTeamsByCountryId(countryId : Int) = repository.getTeamsByCountryId(countryId)

}

interface GetTeams{
    val teams: MutableList<Teams>
    fun getTeam(logo : String, name : String) : List<Teams>{
        teams.add(Teams(logo, name))
        return teams
    }
}

/*interface GetTeams{
    val teams: MutableList<Teams>
    val arrayTeams: ArrayList<Data>

    fun getTeam(team : List<Data>) : List<Teams>{
        for(i in team.indices) {
            arrayTeams.add(team[i])
            teams.add(Teams(team[i].logo, team[i].name))
        }
        return teams
    }
}*/

/*private lateinit var teamsResp : TeamsResponse
val data = mutableListOf<Data>()
val countryId: String
    get() = country_id.toString()
val query: Query
    get() = Query(API, countryId)

interface GetTeams{
    val teams: MutableList<Teams>
    //val arrayTeams: ArrayList<Data>

    fun getTeam() : List<Teams>{
        teamsResp = TeamsResponse(data, query)
        for(i in teamsResp.data.indices){
       //     arrayTeams.add(team[i])
            teams.add(Teams(teamsResp.data[i].logo, teamsResp.data[i].name))
        }
        return teams
    }
}
*/
