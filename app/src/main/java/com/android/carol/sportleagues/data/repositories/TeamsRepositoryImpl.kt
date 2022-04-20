package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.TeamsAPIService
import com.android.carol.sportleagues.data.remote.models.TeamsResponse
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.repositories.TeamsRepository

class TeamsRepositoryImpl constructor(private val teamsAPIService: TeamsAPIService) : TeamsRepository {

    private fun TeamsResponse.toDomainModel(countryId: Int) : Teams{
        val teamsResponse = this.data.first { it.country.countryId == countryId }
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
}