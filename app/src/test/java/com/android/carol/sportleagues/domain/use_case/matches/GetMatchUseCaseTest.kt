package com.android.carol.sportleagues.domain.use_case.matches

import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.data.repositories.FakeMatchesRepository
import com.android.carol.sportleagues.domain.model.MatchesProp
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test


class GetMatchUseCaseTest {

    private lateinit var getMatchUseCase: GetMatchUseCase
    private lateinit var fakeMatchesRepository : FakeMatchesRepository
    private lateinit var matches : MatchesResp

    @Before
    fun setUp() {
        fakeMatchesRepository = FakeMatchesRepository()
        getMatchUseCase = GetMatchUseCase(fakeMatchesRepository)

        val matchesToInsert = mutableListOf<MatchesProp>()
        for(i in matchesToInsert.indices){
            matchesToInsert.add(
                MatchesProp(
                    name_team_home = matchesToInsert[i].name_team_home,
                    name_team_away = matchesToInsert[i].name_team_away,
                    logo_team_home = matchesToInsert[i].logo_team_home,
                    logo_team_away = matchesToInsert[i].logo_team_away,
                    home_score = matchesToInsert[i].home_score,
                    away_score = matchesToInsert[i].away_score
                )
            )
        }

        matchesToInsert.shuffle()

        matches = MatchesResp(matchesToInsert)
        fakeMatchesRepository.insertMatches(matches)
    }

    @Test
    fun testGetMatchApiFunctional() = runBlocking{
        val match = getMatchUseCase.getProp(season_id = season_id)
        assertEquals(matches.matches.size, match.data.size)
    }

    @Test
    fun testGetMatchApiNotFunctional() = runBlocking{
        val match = getMatchUseCase.getProp(season_id = 1)
        assertThat(match.data.size, `is`(0))
    }

    fun testGetProp() {}

    fun testGetMatch() {}
}