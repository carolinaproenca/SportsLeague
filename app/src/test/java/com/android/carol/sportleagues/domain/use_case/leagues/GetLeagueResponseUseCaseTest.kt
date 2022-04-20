package com.android.carol.sportleagues.domain.use_case.leagues

import com.android.carol.sportleagues.data.repositories.FakeLeagueRepository
import org.junit.Before
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.domain.model.League
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetLeagueUseCaseTest {

    private lateinit var getLeagueUseCase: GetLeagueUseCase
    private lateinit var fakeLeagueIdRepository: FakeLeagueRepository
    private lateinit var getLeague: GetLeague
    private lateinit var leagues : LeagueResp
    private val leaguesToInsert = mutableListOf<League>()
    @Before
    fun setUp(){
        fakeLeagueIdRepository = FakeLeagueRepository()
        getLeagueUseCase = GetLeagueUseCase(fakeLeagueIdRepository)

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

    @Test
    fun `If request to network work correctly is Success`() = runTest{
        fakeLeagueIdRepository.setReturnError(true)
        //request success from repository
        val request = fakeLeagueIdRepository.getLeagueId(2)

        assertThat(request, `is`(true))
    }

    @Test
    fun `Get Leagues from remote`() = runTest {
        val league1 = League(123,456,"League1")
        //val league2 = League(321,654,"League2")
        //val remoteLeagues = listOf(league1,league2).sortedBy { it.leagueid }

        val request = fakeLeagueIdRepository.getLeagueById(1)
        assertThat(request,IsEqual(league1))
    }

    @Test
    fun `Test league with interface`() = runTest{
      //  val fakeLeague = League(123,456,"League")
        //fakeLeagueIdRepository.league = fakeLeague
        //getLeague = getLeague.leagues as GetLeague
        getLeague = InterfaceGetLeague(leaguesToInsert)
        val leagueInterface = getLeague.leagues
        assertEquals(leagueInterface, leaguesToInsert)
    }

}

class InterfaceGetLeague(override val leagues: MutableList<League>) : GetLeague{}

/*
@ExperimentalCoroutinesApi
interface GetLeagueTest{
    var fakeLeagueIdRepository: FakeLeagueRepository
    var leagues : LeagueResp

    @Before
    fun setUp(){
        fakeLeagueIdRepository = FakeLeagueRepository()

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

}*/
