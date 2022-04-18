package com.android.carol.sportleagues.domain.use_case.matches

import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.data.repositories.FakeMatchesRepository
import com.android.carol.sportleagues.domain.model.Matches
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetMatchUseCaseTest {

    private lateinit var getMatchUseCase: GetMatchUseCase
    private lateinit var fakeMatchesRepository : FakeMatchesRepository
    private lateinit var matches : MatchesResp
   // private val data = mutableListOf<Data>()

    @Before
    fun setUp() {
        fakeMatchesRepository = FakeMatchesRepository()
        getMatchUseCase = GetMatchUseCase(fakeMatchesRepository)

        val matchesToInsert = mutableListOf<Matches>()
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

 /*   @Test
    fun testGetMatchApiFunctional() = runBlocking{
        val match = getMatchUseCase.getProp(season_id)
        assertEquals(matches.matches.size, match.data.size)
    }

    @Test
    fun testGetMatchApiNotFunctional() = runBlocking{
        val match = getMatchUseCase.getProp(seasonId = 1)
        assertThat(match.data.size, `is`(0))
    }

    @Test
    fun testGetMatchHomeTeamName() = runBlocking{
        val match = getMatchUseCase.getMatch(data)
        for(i in 0..match.size){
            assertEquals(matches.matches[i].name_team_home, match[i].name_team_home)
        }
    }

    @Test
    fun testGetMatchAwayTeamName() = runBlocking{
        val match = getMatchUseCase.getMatch(data)
        for(i in 0..match.size){
            assertEquals(matches.matches[i].name_team_away, match[i].name_team_away)
        }
    }

    @Test
    fun testGetMatchHomeTeamLogo() = runBlocking{
        val match = getMatchUseCase.getMatch(data)
        for(i in 0..match.size){
            assertEquals(matches.matches[i].logo_team_home, match[i].logo_team_home)
        }
    }

    @Test
    fun testGetMatchAwayTeamLogo() = runBlocking{
        val match = getMatchUseCase.getMatch(data)
        for(i in 0..match.size){
            assertEquals(matches.matches[i].logo_team_away, match[i].logo_team_away)
        }
    }

    @Test
    fun testGetMatchHomeTeamScore() = runBlocking{
        val match = getMatchUseCase.getMatch(data)
        for(i in 0..match.size){
            assertEquals(matches.matches[i].home_score, match[i].home_score)
        }
    }

    @Test
    fun testGetMatchAwayTeamScore() = runBlocking{
        val match = getMatchUseCase.getMatch(data)
        for(i in 0..match.size){
            assertEquals(matches.matches[i].away_score, match[i].away_score)
        }
    }*/
}