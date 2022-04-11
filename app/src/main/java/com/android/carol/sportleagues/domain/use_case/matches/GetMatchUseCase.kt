package com.android.carol.sportleagues.domain.use_case.matches

import com.android.carol.sportleagues.data.remote.dtoMatches.Data
import com.android.carol.sportleagues.domain.model.MatchesProp
import com.android.carol.sportleagues.domain.repositories.DMatchesRepository

class GetMatchUseCase constructor(private val repository: DMatchesRepository){

    suspend fun getProp(season_id : Int) = repository.getProp(season_id)

    private val matches = mutableListOf<MatchesProp>()
    private val arrayMatches = arrayListOf<Data>()

    fun getMatch(match :List<Data>) : List<MatchesProp>{
        for(i in match.indices){
            arrayMatches.add(match[i])
            matches.add(MatchesProp(match[i].homeTeam.name,match[i].awayTeam.name, match[i].homeTeam.logo,
            match[i].awayTeam.logo, match[i].stats.homeScore, match[i].stats.awayScore))

        }
        return matches
    }
}