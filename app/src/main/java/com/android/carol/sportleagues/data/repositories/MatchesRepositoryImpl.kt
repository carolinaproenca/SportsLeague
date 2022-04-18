package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.MatchesAPIService
import com.android.carol.sportleagues.data.remote.models.MatchesResponse
import com.android.carol.sportleagues.domain.model.Matches
import com.android.carol.sportleagues.domain.repositories.MatchesRepository

class MatchesRepositoryImpl constructor(private val matchesAPIService: MatchesAPIService) : MatchesRepository {

    private fun MatchesResponse.toDomainModel(seasonId: Int) : Matches{
        val matchesResponse = this.data.first{ it.seasonId == seasonId}
        return Matches(matchesResponse.homeTeam.name, matchesResponse.awayTeam.name, matchesResponse.homeTeam.logo,
        matchesResponse.awayTeam.logo, matchesResponse.stats.homeScore, matchesResponse.stats.awayScore)
    }

    override suspend fun getMatchesBySeasonId(seasonId: Int): Matches {
        return matchesAPIService.getProperties(season_id = seasonId).toDomainModel(seasonId)
    }
}
