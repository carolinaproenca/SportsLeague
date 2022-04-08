package com.android.carol.sportleagues.domain.repositories

import com.android.carol.sportleagues.data.remote.dtoMatches.Matches

interface DMatchesRepository {
    suspend fun getProp() : Matches
}