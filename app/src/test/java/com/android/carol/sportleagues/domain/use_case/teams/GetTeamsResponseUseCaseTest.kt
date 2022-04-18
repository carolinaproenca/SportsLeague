package com.android.carol.sportleagues.domain.use_case.teams

import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.data.repositories.FakeTeamsRepository
import com.android.carol.sportleagues.domain.model.Teams
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetTeamsResponseUseCaseTest {

    private lateinit var getTeamsUseCase: GetTeamsUseCase
    private lateinit var fakeTeamsRepository: FakeTeamsRepository
    private lateinit var teamsResp : TeamsResp
    private val data = mutableListOf<Data>()

    @Before
    fun setup(){

        fakeTeamsRepository = FakeTeamsRepository()
        getTeamsUseCase = GetTeamsUseCase(fakeTeamsRepository)

        val teamsToInsert = mutableListOf<Teams>()
        for(i in teamsToInsert.indices){
            teamsToInsert.add(
                Teams(
                    logo = teamsToInsert[i].logo,
                    name = teamsToInsert[i].name
                )
            )
        }

        teamsToInsert.shuffle()

        teamsResp = TeamsResp(teamsToInsert)
        fakeTeamsRepository.insertTeam(teamsResp)
    }

 /*   @Test
    fun testGetLeagueApiFunctional() = runBlocking{
        val teams = getTeamsUseCase.getProp(country_id)
        assertEquals(teamsResp.teams.size, teams.data.size)
    }

    @Test
    fun testGetLeagueApiNotFunctional() = runBlocking{
        val teams = getTeamsUseCase.getProp(1)
        assertThat(teams.data.size, `is`(0))
    }

    @Test
    fun testGetLeagueName() = runBlocking{
        val teams = getTeamsUseCase.getTeam(data)
        for(i in 0..teams.size){
            assertEquals(teamsResp.teams[i].name, teams[i].name)
        }
    }

    @Test
    fun testGetLeagueLogo() = runBlocking{
        val teams = getTeamsUseCase.getTeam(data)
        for(i in 0..teams.size){
            assertEquals(teamsResp.teams[i].logo, teams[i].logo)
        }
    }*/
}