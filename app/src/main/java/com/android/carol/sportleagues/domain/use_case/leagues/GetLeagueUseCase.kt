package com.android.carol.sportleagues.domain.use_case.leagues

import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueId
import com.android.carol.sportleagues.data.remote.dtoTeams.Teams
import com.android.carol.sportleagues.domain.model.LeagueProp
import com.android.carol.sportleagues.domain.repositories.DLeagueIdRepository


class GetLeagueUseCase /*constructor(private val repository: DLeagueIdRepository)*/{

   // suspend fun getProp() = repository.getProp()

    private val leagues = mutableListOf<LeagueProp>()
    private val arrayLeagues = arrayListOf<LeagueId>()

    fun getLeague(league : List<LeagueId>) : List<LeagueProp>{
        for(i in league.indices){
            arrayLeagues.add(league[i])
            leagues.add(
                LeagueProp(league[i].data[i].leagueId,league[i].data[i].countryId,
                    league[i].data[i].name,league[i+1].data[i+1].leagueId,league[i+1].data[i+1].countryId,
                    league[i+1].data[i+1].name)
            )
        }
        return leagues
    }
}