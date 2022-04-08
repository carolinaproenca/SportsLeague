package com.android.carol.sportleagues.domain.use_case.season

import com.android.carol.sportleagues.data.remote.dtoSeasons.Data
import com.android.carol.sportleagues.data.remote.dtoSeasons.Seasons
import com.android.carol.sportleagues.domain.model.SeasonProp
import com.android.carol.sportleagues.domain.repositories.DSeasonsRepository

class GetSeasonUseCase /*constructor(private val repository: DSeasonsRepository)*/ {

//    suspend fun getProp() = repository.getProp()

    private val seasons = mutableListOf<SeasonProp>()
    private val arraySeason = arrayListOf<Data>()

    fun getSeason(season :List<Data>) : List<SeasonProp>{
        for(i in season.indices){
            arraySeason.add(season[i])
            seasons.add(SeasonProp(season[i].name, season[i].seasonId))
        }
        return seasons
    }
}