package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.common.Constants
import com.android.carol.sportleagues.data.remote.dtoMatches.Matches
import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.data.remote.dtoMatches.Data
import com.android.carol.sportleagues.domain.repositories.DMatchesRepository
import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.data.remote.dtoMatches.Query
import java.io.IOException

class FakeMatchesRepository : DMatchesRepository {

    private lateinit var matches: Matches
    private val match = mutableListOf<MatchesResp>()
    private val data = mutableListOf<Data>()
    private val seasonStr = season_id.toString()
    private val query = Query(Constants.API,seasonStr)

    override suspend fun getProp(seasonid: Int): Matches {
        matches = Matches(data, query)
        return if(seasonid == season_id)
            matches
        else{
            Matches(data, query)
        }
    }

    fun insertMatches(matchesResp: MatchesResp){
        match.add(matchesResp)
    }

}