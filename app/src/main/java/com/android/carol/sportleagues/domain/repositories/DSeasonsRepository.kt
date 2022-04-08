package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.data.remote.dtoSeasons.Seasons

interface DSeasonsRepository {
    suspend fun getProp() : Seasons
}