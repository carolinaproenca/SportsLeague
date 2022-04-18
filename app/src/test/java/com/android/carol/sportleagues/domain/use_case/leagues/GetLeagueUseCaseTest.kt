package com.android.carol.sportleagues.domain.use_case.leagues


import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data
import com.android.carol.sportleagues.data.repositories.FakeLeagueRepository
import org.junit.Before
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.domain.model.League
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetLeagueUseCaseTest {

    private lateinit var getLeagueUseCase: GetLeagueUseCase
    private lateinit var fakeLeagueIdRepository: FakeLeagueRepository
    private lateinit var leagues : LeagueResp
    //private val data = mutableListOf<Data>()


    @Before
    fun setUp(){
        fakeLeagueIdRepository = FakeLeagueRepository()
        getLeagueUseCase = GetLeagueUseCase(fakeLeagueIdRepository)

        val leaguesToInsert = mutableListOf<League>()
        for(i in leaguesToInsert.indices){
            leaguesToInsert.add(
                League(
                    leagueid = leaguesToInsert[i].leagueid,
                    countryid = leaguesToInsert[i].countryid,
                    name = leaguesToInsert[i].name
                )
            )
        }
        leaguesToInsert.shuffle()

        leagues = LeagueResp(leaguesToInsert)
        fakeLeagueIdRepository.insertLeagues(leagues)

    }
/*
    @Test
    fun testGetLeagueApiFunctional() = runBlocking{
        val league = getLeagueUseCase.getProp(league_id)
        assertEquals(leagues.league.size, league.data.size)
    }

    @Test
    fun testGetLeagueApiNotFunctional() = runBlocking{
        val league = getLeagueUseCase.getProp(false)
        assertThat(league.data.size, `is`(0))
    }

    @Test
    fun testGetLeagueCountryId() = runBlocking{
        val league = getLeagueUseCase.getLeague(data) as MutableList<League>
        for(i in 0..leagues.league.size){
            assertEquals(leagues.league[i].countryid, league[i].countryid)
           // assertThat(league.size, `is`(0))
        }
    }

    @Test
    fun testGetLeagueLeagueId() = runBlocking{
        val league = getLeagueUseCase.getLeague(data)
        for(i in 0..league.size){
            assertThat(league[i].leagueid, not(league[i-1].leagueid))
        }
    }

    @Test
    fun testGetLeagueName() = runBlocking{
        val league = getLeagueUseCase.getLeague(data)
        for(i in 0..league.size){
            assertThat(league[i].name, not(league[i-1].name))
        }
    }*/
}