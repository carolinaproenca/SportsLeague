package com.android.carol.sportleagues.data.repositories

import com.android.carol.sportleagues.data.Result.Success
import com.android.carol.sportleagues.data.Result.Error
import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.domain.repositories.MatchesRepository
import com.android.carol.sportleagues.domain.model.Matches
import java.lang.Exception

class FakeMatchesRepository : MatchesRepository {

    lateinit var matches: Matches
    private val match = mutableListOf<MatchesResp>()
    private val matchesList = mutableListOf<Matches>()

    private var shouldReturnSuccess = false

    fun setReturnSuccess(value : Boolean){
        shouldReturnSuccess = value
    }

    override suspend fun getMatchesBySeasonId(seasonId: Int): Matches {
        matches = Matches("Home","Away","123","456",3,2)
        return matches
    }

    override suspend fun getMatchesSeasonId(id : Int) : Boolean{
        matches = Matches("Home","Away","123","456",3,2)
        return if(shouldReturnSuccess){
            Success(id)
            true
        }else {
            Error(Exception("Matches Test Exception"))
            false
        }
    }

    override fun getMatch(): List<Matches> {
        return matchesList
    }

    fun insertMatches(matchesResp: MatchesResp){
        match.add(matchesResp)
    }

}