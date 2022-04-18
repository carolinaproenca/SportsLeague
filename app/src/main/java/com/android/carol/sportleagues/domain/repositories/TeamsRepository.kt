package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.domain.model.Teams

interface TeamsRepository {
    suspend fun getTeamsByCountryId(countryId : Int) : Teams
}