package com.android.carol.sportleagues

import com.android.carol.sportleagues.data.repositories.TeamsRepository
import com.android.carol.sportleagues.domain.use_case.teams.GetTeamsUseCase

class SportTeamsContainer(repository: TeamsRepository) {

    val teamsUseCase = GetTeamsUseCase(repository)
}