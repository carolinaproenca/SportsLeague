package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.common.Constants.API
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.data.remote.dtoTeams.Query
import com.android.carol.sportleagues.data.remote.dtoTeams.Teams
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.domain.repositories.DTeamsRepository
import java.io.IOException

class FakeTeamsRepository : DTeamsRepository {

    private lateinit var teams :Teams
    private val team = mutableListOf<TeamsResp>()
    private val data = mutableListOf<Data>()
    private val countryIdSTr = country_id.toString()
    private val query = Query(API,countryIdSTr)


    override suspend fun getProp(countryid: Int): Teams {
        teams = Teams(data, query)
        return if(countryid == country_id)
            teams
        else{
            Teams(data, query)
        }
    }

    fun insertTeam(teamResp : TeamsResp){
        team.add(teamResp)
    }
}