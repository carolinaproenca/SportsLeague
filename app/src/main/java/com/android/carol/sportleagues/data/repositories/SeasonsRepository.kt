package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.data.remote.SeasonsAPIService
import com.android.carol.sportleagues.data.remote.dtoSeasons.Seasons
import com.android.carol.sportleagues.domain.repositories.DSeasonsRepository

class SeasonsRepository constructor(private val seasonsAPIService: SeasonsAPIService) : DSeasonsRepository {
    override suspend fun getProp(): Seasons {
        return seasonsAPIService.getProperties(league_id = league_id)
    }
}