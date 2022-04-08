package com.android.carol.sportleagues.domain.use_case.matches

import com.android.carol.sportleagues.data.remote.dtoMatches.Data
import com.android.carol.sportleagues.domain.model.MatchesProp

class GetMatchUseCase /*constructor(private val repository: DMatchesRepository)*/{

//    suspend fun getProp() = repository.getProp()

    private val matches = mutableListOf<MatchesProp>()
    private val arrayMatches = arrayListOf<Data>()

    fun getMatch(match :List<Data>) : List<MatchesProp>{
        for(i in match.indices){
            arrayMatches.add(match[i])
            matches.add(MatchesProp(match[i].homeTeam.name,match[i].awayTeam.name, match[i].homeTeam.logo,
            match[i].awayTeam.logo, match[i].stats.homeScore, match[i].stats.awayScore))

            /*MatchesProp(match[i].data[i].homeTeam.name,match[i].data[i].awayTeam.name,
                match[i].data[i].homeTeam.logo, match[i].data[i].awayTeam.logo,
                match[i].data[i].stats.homeScore, match[i].data[i].stats.awayScore)*/
        }
        return matches
    }
}