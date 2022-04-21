package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.LeagueIdAPIService
import com.android.carol.sportleagues.data.remote.models.LeagueResponse
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.repositories.LeagueRepository
import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data

class LeagueRepositoryImpl constructor(private val leagueIdAPIService: LeagueIdAPIService) : LeagueRepository {

    private lateinit var leagueResponse : Data
    private lateinit var leaResponse : List<Data>

    private fun LeagueResponse.toDomainModel(id: Int) : League {
        leagueResponse = this.data.first { it.leagueId == id }
        leaResponse = this.data
        return League(leagueResponse.leagueId, leagueResponse.countryId, leagueResponse.name)
    }

    override suspend fun getLeagueById(id : Int): League {
        return leagueIdAPIService.getProperties(subscribed = true).toDomainModel(id)
    }

    override suspend fun getLeagueId(id : Int) : Boolean {
        return try {
            val request = leagueIdAPIService.getProperties(subscribed = true).toDomainModel(id)
            Success(request)
            true
        }catch (e: Exception){
            Error(e)
            false
        }
    }

    override fun getLeague(): List<League> {
        val leagues = mutableListOf<League>()
        for(i in leaResponse.indices){
            leagues.add(League(leaResponse[i].leagueId, leaResponse[i].countryId, leaResponse[i].name))
        }
        return leagues
    }

}