package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.common.Constants.API
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.data.remote.dtoSeasons.Data
import com.android.carol.sportleagues.data.remote.dtoSeasons.Query
import com.android.carol.sportleagues.data.remote.dtoSeasons.Seasons
import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.domain.repositories.DSeasonsRepository
import java.io.IOException

class FakeSeasonsRepository : DSeasonsRepository {

    private lateinit var seasons : Seasons
    private val season = mutableListOf<SeasonsResp>()
    private val data = mutableListOf<Data>()
    private val leagueidStr = league_id.toString()
    private val query = Query(API, leagueidStr)

    override suspend fun getProp(leagueid: Int): Seasons {
        seasons = Seasons(data,query)
        return if(leagueid == league_id)
            seasons
        else{
            Seasons(data, query)
        }
    }

    fun insertSeasons(seasonResp : SeasonsResp){
        season.add(seasonResp)
    }
}