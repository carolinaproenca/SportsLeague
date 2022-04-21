package com.android.carol.sportleagues.domain.use_case.matches

import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.data.repositories.FakeMatchesRepository
import com.android.carol.sportleagues.domain.model.Matches
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetMatchUseCaseTest {

    private lateinit var getMatchUseCase: GetMatchUseCase
    private lateinit var fakeMatchesRepository : FakeMatchesRepository
    private lateinit var matches : MatchesResp
    private val matchesToInsert = mutableListOf<Matches>()

    @Before
    fun setUp() {
        fakeMatchesRepository = FakeMatchesRepository()
        getMatchUseCase = GetMatchUseCase(fakeMatchesRepository)

        for(i in matchesToInsert.indices){
            matchesToInsert.add(
                Matches(
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
    fun `If request to network work correctly is Success`() = runTest{
        fakeMatchesRepository.setReturnSuccess(true)
        //request success from repository
        val request = fakeMatchesRepository.getMatchesSeasonId(1)
        assertThat(request, `is`(true))
    }

    @Test
    fun `Get Matches from remote`() = runTest {
        val matches = Matches("Home", "Away", "123", "456", 3,2)
        val request = fakeMatchesRepository.getMatchesBySeasonId(1)
        assertThat(request, IsEqual(matches))
    }
/*

    @Test
    fun `Test Matches with interface`() = runTest{
        getMatches = InterfaceGetMatches(matchesToInsert)
        val matchInterface = getMatches.matches
        assertEquals(matchInterface, matchesToInsert)
    }
*/

}
