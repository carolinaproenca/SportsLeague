package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.TeamsAPIService
import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.data.remote.models.TeamsResponse
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.repositories.TeamsRepository

class TeamsRepositoryImpl constructor(private val teamsAPIService: TeamsAPIService) : TeamsRepository {

    private lateinit var teamsResponse: Data
    private lateinit var teaResponse : List<Data>

    private fun TeamsResponse.toDomainModel(countryId: Int) : Teams{
        teamsResponse = this.data.first { it.country.countryId == countryId }
        teaResponse = this.data
        return Teams(teamsResponse.logo, teamsResponse.name)
    }

    override suspend fun getTeamsByCountryId(countryId: Int): Teams {
        return teamsAPIService.getProperties(country_id = countryId).toDomainModel(countryId)
    }

    override suspend fun getTeamsCountryId(id : Int) : Boolean{
        return try {
            val request = teamsAPIService.getProperties(country_id = id).toDomainModel(id)
            Success(request)
            true
        }catch (e: Exception){
            Error(e)
            false
        }
    }

    override fun getTeam(): List<Teams> {
        val teams = mutableListOf<Teams>()
        for(i in teaResponse.indices){
            teams.add(Teams(teaResponse[i].logo, teaResponse[i].name))
        }
        return teams
    }
}