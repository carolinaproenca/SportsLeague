package com.android.carol.sportleagues.domain.use_case.matches

import com.android.carol.sportleagues.domain.model.Matches
import com.android.carol.sportleagues.domain.repositories.MatchesRepository

class GetMatchUseCase constructor(private val repository: MatchesRepository){

    suspend fun getMatchesBySeasonId(seasonId : Int) = repository.getMatchesBySeasonId(seasonId)
}

interface GetMatches{
    val matches: MutableList<Matches>
    fun getMatch(homeTeam_name: String,awayTeam_name: String,homeTeam_logo: String,awayTeam_logo: String,
                 stats_homeScore : Int, stats_awayScore : Int) : List<Matches>{
        matches.add(Matches(homeTeam_name,awayTeam_name, homeTeam_logo,
                awayTeam_logo, stats_homeScore, stats_awayScore))
        return matches
    }
}

/*
interface GetMatches{
    val matches: MutableList<Matches>
    val arrayMatches: ArrayList<Data>

    fun getMatch(match :List<Data>) : List<Matches>{
        for(i in match.indices){
            arrayMatches.add(match[i])
            matches.add(Matches(match[i].homeTeam.name,match[i].awayTeam.name, match[i].homeTeam.logo,
                match[i].awayTeam.logo, match[i].stats.homeScore, match[i].stats.awayScore))

        }
        return matches
    }
}*/
