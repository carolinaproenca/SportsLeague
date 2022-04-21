package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.domain.model.Teams

interface TeamsRepository {
    suspend fun getTeamsByCountryId(countryId : Int) : Teams
    suspend fun getTeamsCountryId(id : Int) : Boolean
    fun getTeam() : List<Teams>
}