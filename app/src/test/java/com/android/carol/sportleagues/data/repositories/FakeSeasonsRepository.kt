package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result
import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.repositories.SeasonsRepository
import java.lang.Exception

class FakeSeasonsRepository : SeasonsRepository {

    lateinit var seasons : Season
    private val season = mutableListOf<SeasonsResp>()

    private var shouldReturnError = false

    fun setReturnError(value : Boolean){
        shouldReturnError = value
    }

    override suspend fun getSeasonByLeagueId(leagueId: Int): Season {
        seasons = Season("Season1",1)
        return seasons
    }

    override suspend fun getSeasonId(id : Int) : Boolean{
        seasons = Season("Season1",1)
        return if(shouldReturnError){
            Result.Success(id)
            true
        }else {
            Result.Error(Exception("Season Test Exception"))
            false
        }
    }

    fun insertSeasons(seasonResp : SeasonsResp){
        season.add(seasonResp)
    }

}