package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.repositories.SeasonsRepository
import java.lang.Exception

class FakeSeasonsRepository : SeasonsRepository {

    lateinit var seasons : Season
    private val season = mutableListOf<SeasonsResp>()
    private val seasonsList = mutableListOf<Season>()

    private var shouldReturnSuccess = false

    fun setReturnSuccess(value : Boolean){
        shouldReturnSuccess = value
    }

    override suspend fun getSeasonByLeagueId(leagueId: Int): Season {
        seasons = Season("Season1",1)
        return seasons
    }

    override suspend fun getSeasonId(id : Int) : Boolean{
        seasons = Season("Season1",1)
        return if(shouldReturnSuccess){
            Success(id)
            true
        }else {
            Error(Exception("Season Test Exception"))
            false
        }
    }

    override fun getSeason(): List<Season> {
        return seasonsList
    }

    fun insertSeasons(seasonResp : SeasonsResp){
        season.add(seasonResp)
    }

}