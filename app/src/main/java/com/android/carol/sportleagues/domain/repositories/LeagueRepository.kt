package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.data.remote.models.LeagueResponse
import com.android.carol.sportleagues.domain.model.League

interface LeagueRepository {
    suspend fun getLeagueById(id : Int) : League
}