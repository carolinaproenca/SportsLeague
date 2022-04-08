package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.common.Constants.country_id
import com.android.carol.sportleagues.data.remote.TeamsAPIService
import com.android.carol.sportleagues.data.remote.dtoTeams.Teams
import com.android.carol.sportleagues.domain.repositories.DTeamsRepository

class TeamsRepository constructor(private val teamsAPIService: TeamsAPIService) : DTeamsRepository{
    override suspend fun getProp(): Teams {
        return teamsAPIService.getProperties(country_id = country_id)
    }
}