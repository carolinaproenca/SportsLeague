package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.domain.repositories.MatchesRepository
import com.android.carol.sportleagues.domain.model.Matches

class FakeMatchesRepository :
    MatchesRepository {

    private lateinit var matches: Matches
    private val match = mutableListOf<MatchesResp>()

    override suspend fun getMatchesBySeasonId(seasonId: Int): Matches {
        return matches
    }

    fun insertMatches(matchesResp: MatchesResp){
        match.add(matchesResp)
    }

}