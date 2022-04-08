package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueId

interface DLeagueIdRepository {
    suspend fun getProp() : LeagueId
}