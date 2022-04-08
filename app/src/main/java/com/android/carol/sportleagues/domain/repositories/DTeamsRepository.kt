package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.data.remote.dtoTeams.Teams

interface DTeamsRepository {
    suspend fun getProp() : Teams
}