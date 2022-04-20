package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result
import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.domain.repositories.MatchesRepository
import com.android.carol.sportleagues.domain.model.Matches
import java.lang.Exception

class FakeMatchesRepository : MatchesRepository {

    lateinit var matches: Matches
    private val match = mutableListOf<MatchesResp>()
    private var shouldReturnError = false

    fun setReturnError(value : Boolean){
        shouldReturnError = value
    }

    override suspend fun getMatchesBySeasonId(seasonId: Int): Matches {
        matches = Matches("Home","Away","123","456",3,2)
        return matches
    }

    override suspend fun getMatchesSeasonId(id : Int) : Boolean{
        matches = Matches("Home","Away","123","456",3,2)
        return if(shouldReturnError){
            Result.Success(id)
            true
        }else {
            Result.Error(Exception("Matches Test Exception"))
            false
        }
    }

    fun insertMatches(matchesResp: MatchesResp){
        match.add(matchesResp)
    }

}