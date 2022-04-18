package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.repositories.SeasonsRepository

class FakeSeasonsRepository : SeasonsRepository {

    private lateinit var seasons : Season
    private val season = mutableListOf<SeasonsResp>()

    override suspend fun getSeasonByLeagueId(leagueId: Int): Season {
        return seasons
    }

    fun insertSeasons(seasonResp : SeasonsResp){
        season.add(seasonResp)
    }

}