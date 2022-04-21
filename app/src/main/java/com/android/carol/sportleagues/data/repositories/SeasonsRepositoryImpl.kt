package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.SeasonsAPIService
import com.android.carol.sportleagues.data.remote.dtoSeasons.Data
import com.android.carol.sportleagues.data.remote.models.SeasonsResponse
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.repositories.SeasonsRepository

class SeasonsRepositoryImpl constructor(private val seasonsAPIService: SeasonsAPIService) : SeasonsRepository {

    private lateinit var seasonsResponse :Data
    private lateinit var seasResponse: List<Data>

    private fun SeasonsResponse.toDomainModel(leagueId: Int) : Season {
        seasonsResponse = this.data.first {it.leagueId == leagueId}
        seasResponse = this.data
        return Season(seasonsResponse.name, seasonsResponse.seasonId)
    }

    override suspend fun getSeasonByLeagueId(leagueId: Int): Season {
        return seasonsAPIService.getProperties(league_id = leagueId).toDomainModel(leagueId)
    }

    override suspend fun getSeasonId(id : Int) : Boolean{
        return try {
            val request = seasonsAPIService.getProperties(league_id = id).toDomainModel(id)
            Success(request)
            true
        }catch (e: Exception){
            Error(e)
            false
        }
    }

    override fun getSeason(): List<Season> {
        val seasons = mutableListOf<Season>()
        for(i in seasResponse.indices){
            seasons.add(Season(seasResponse[i].name,seasResponse[i].seasonId))
        }
        return seasons
    }

}