package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.MatchesAPIService
import com.android.carol.sportleagues.data.remote.dtoMatches.Matches
import com.android.carol.sportleagues.domain.repositories.DMatchesRepository

class MatchesRepository constructor(private val matchesAPIService: MatchesAPIService) : DMatchesRepository{

    override suspend fun getProp(season_id: Int): Matches {
        return matchesAPIService.getProperties(season_id = season_id)
    }

}