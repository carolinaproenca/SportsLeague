package com.android.carol.sportleagues

import com.android.carol.sportleagues.data.repositories.LeagueIdRepository
import com.android.carol.sportleagues.domain.use_case.leagues.GetLeagueUseCase

class SportLeagueContainer(leagueIdRepository: LeagueIdRepository) {
    val getLeagueUseCase = GetLeagueUseCase(leagueIdRepository)
}