package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.domain.model.Matches

interface MatchesRepository {
    suspend fun getMatchesBySeasonId(seasonId : Int) : Matches
}