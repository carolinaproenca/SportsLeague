package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.domain.model.Season

interface SeasonsRepository {
    suspend fun getSeasonByLeagueId(leagueId : Int) : Season
}