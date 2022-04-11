package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.LeagueIdAPIService
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueId
import com.android.carol.sportleagues.domain.repositories.DLeagueIdRepository

class LeagueIdRepository constructor(private val leagueIdAPIService: LeagueIdAPIService) : DLeagueIdRepository {

    override suspend fun getProp(subscribed: Boolean): LeagueId {
        return leagueIdAPIService.getProperties(subscribed = subscribed)
    }
}