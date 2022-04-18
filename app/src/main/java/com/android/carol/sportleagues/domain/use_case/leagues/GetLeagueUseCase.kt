package com.android.carol.sportleagues.domain.use_case.leagues

import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data
import com.android.carol.sportleagues.domain.model.League

import com.android.carol.sportleagues.domain.repositories.LeagueRepository

class GetLeagueUseCase constructor(private val repository: LeagueRepository){

    suspend fun getLeagueById(id : Int) = repository.getLeagueById(id)

}

interface GetLeague{

    val leagues: MutableList<League>
    val arrayLeagues: ArrayList<Data>

    fun getLeague(league: List<Data>): List<League> {
        for (i in league.indices) {
            arrayLeagues.add(league[i])
            leagues.add(
                League(
                    league[i].leagueId, league[i].countryId,
                    league[i].name
                )
            )
        }
        return leagues
    }

  /*  val leagues: MutableList<League>
    val arrayLeagues: ArrayList<Data>

    companion object : GetLeague {

        override val leagues: MutableList<League>
            get() = leagues
        override val arrayLeagues: ArrayList<Data>
            get() = arrayLeagues

        fun getLeague(league: List<Data>): List<League> {
            for (i in league.indices) {
                arrayLeagues.add(league[i])
                leagues.add(
                    League(
                        league[i].leagueId, league[i].countryId,
                        league[i].name
                    )
                )
            }
            return leagues
        }
    }*/
}
