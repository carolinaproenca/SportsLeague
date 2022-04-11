package com.android.carol.sportleagues

import com.android.carol.sportleagues.data.repositories.SeasonsRepository
import com.android.carol.sportleagues.domain.use_case.season.GetSeasonUseCase

class SportSeasonContainer(repository: SeasonsRepository) {
    val seasonUseCase = GetSeasonUseCase(repository)
}