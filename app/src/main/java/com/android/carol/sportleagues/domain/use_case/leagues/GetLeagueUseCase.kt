package com.android.carol.sportleagues.domain.use_case.leagues

import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data
import com.android.carol.sportleagues.domain.model.LeagueProp

class GetLeagueUseCase /*constructor(private val repository: DLeagueIdRepository)*/{

   // suspend fun getProp() = repository.getProp()

    private val leagues = mutableListOf<LeagueProp>()
    private val arrayLeagues = arrayListOf<Data>()

    fun getLeague(league : List<Data>) : List<LeagueProp>{
        for(i in league.indices){
            arrayLeagues.add(league[i])
            leagues.add(
                LeagueProp(league[i].leagueId,league[i].countryId,
                    league[i].name)

              /*  LeagueProp(league[i].data[i].leagueId,league[i].data[i].countryId,
                    league[i].data[i].name,league[i+1].data[i+1].leagueId,league[i+1].data[i+1].countryId,
                    league[i+1].data[i+1].name)*/
            )
        }
        return leagues
    }
}