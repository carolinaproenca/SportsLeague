package com.android.carol.sportleagues.domain.use_case.teams

import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.data.repositories.FakeTeamsRepository
import com.android.carol.sportleagues.domain.model.TeamsProp
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class GetTeamsUseCaseTest {

    private lateinit var getTeamsUseCase: GetTeamsUseCase
    private lateinit var fakeTeamsRepository: FakeTeamsRepository
    private lateinit var teamsResp : TeamsResp

    @Before
    fun setup(){

        fakeTeamsRepository = FakeTeamsRepository()
        getTeamsUseCase = GetTeamsUseCase(fakeTeamsRepository)

        val teamsToInsert = mutableListOf<TeamsProp>()
        for(i in teamsToInsert.indices){
            teamsToInsert.add(
                TeamsProp(
                    logo = teamsToInsert[i].logo,
                    name = teamsToInsert[i].name
                )
            )
        }

        teamsToInsert.shuffle()

        teamsResp = TeamsResp(teamsToInsert)
        fakeTeamsRepository.insertTeam(teamsResp)
    }

    @Test
    fun testGetLeagueApiFunctional() = runBlocking{
        val teams = getTeamsUseCase.getProp(country_id)
        assertEquals(teamsResp.teams.size, teams.data.size)
    }

    @Test
    fun testGetLeagueApiNotFunctional() = runBlocking{
        val teams = getTeamsUseCase.getProp(1)
        assertThat(teams.data.size, `is`(0))
    }

}