package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.LeagueIdAPIService
import com.android.carol.sportleagues.data.remote.models.LeagueResponse
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.repositories.LeagueRepository

class LeagueRepositoryImpl constructor(private val leagueIdAPIService: LeagueIdAPIService) : LeagueRepository {

    private fun LeagueResponse.toDomainModel(id: Int) : League {
        val leagueResponse = this.data.first { it.leagueId == id }
        return League(leagueResponse.leagueId, leagueResponse.countryId, leagueResponse.name)
    }

    override suspend fun getLeagueById(id : Int): League {
        return leagueIdAPIService.getProperties(subscribed = true).toDomainModel(id)
    }

}