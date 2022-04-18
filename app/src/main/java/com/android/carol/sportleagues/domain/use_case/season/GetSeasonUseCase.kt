package com.android.carol.sportleagues.domain.use_case.season

import com.android.carol.sportleagues.data.remote.dtoSeasons.Data
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.repositories.SeasonsRepository

class GetSeasonUseCase constructor(private val repository: SeasonsRepository) {

    suspend fun getSeasonByLeagueId(leagueId : Int) = repository.getSeasonByLeagueId(leagueId)

}

interface GetSeason{
    val seasons: MutableList<Season>
    val arraySeason: ArrayList<Data>

    fun getSeason(season : List<Data>) : List<Season>{
        for(i in season.indices){
            arraySeason.add(season[i])
            seasons.add(Season(season[i].name, season[i].seasonId))
        }
        return seasons
    }
}