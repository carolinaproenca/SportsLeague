package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.data.remote.TeamsAPIService
import com.android.carol.sportleagues.data.remote.dtoTeams.Teams
import com.android.carol.sportleagues.domain.repositories.DTeamsRepository

class TeamsRepository constructor(private val teamsAPIService: TeamsAPIService) : DTeamsRepository{

    override suspend fun getProp(country_id: Int): Teams {
        return teamsAPIService.getProperties(country_id = country_id)
    }
}