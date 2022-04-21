package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.MatchesAPIService
import com.android.carol.sportleagues.data.remote.dtoMatches.Data
import com.android.carol.sportleagues.data.remote.models.MatchesResponse
import com.android.carol.sportleagues.domain.model.Matches
import com.android.carol.sportleagues.domain.repositories.MatchesRepository

class MatchesRepositoryImpl constructor(private val matchesAPIService: MatchesAPIService) : MatchesRepository {

    private lateinit var matchesResponse : Data
    private lateinit var matResponse : List<Data>

    private fun MatchesResponse.toDomainModel(seasonId: Int) : Matches{
        matchesResponse = this.data.first{ it.seasonId == seasonId}
        matResponse = this.data
        return Matches(matchesResponse.homeTeam.name, matchesResponse.awayTeam.name, matchesResponse.homeTeam.logo,
        matchesResponse.awayTeam.logo, matchesResponse.stats.homeScore, matchesResponse.stats.awayScore)
    }

    override suspend fun getMatchesBySeasonId(seasonId: Int): Matches {
        return matchesAPIService.getProperties(season_id = seasonId).toDomainModel(seasonId)
    }
    override suspend fun getMatchesSeasonId(id : Int) : Boolean{
        return try {
            val request = matchesAPIService.getProperties(season_id = id).toDomainModel(id)
            Success(request)
            true
        }catch (e: Exception){
            Error(e)
            false
        }
    }

    override fun getMatch() : List<Matches>{
        val matches = mutableListOf<Matches>()
        for(i in matResponse.indices) {
            matches.add(
                Matches(
                    matResponse[i].homeTeam.name,matResponse[i].awayTeam.name, matResponse[i].homeTeam.logo,
                    matResponse[i].awayTeam.logo, matResponse[i].stats.homeScore, matResponse[i].stats.awayScore
                )
            )
        }
        return matches
    }
}
