package com.android.carol.sportleagues.domain.use_case.teams

import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.data.repositories.FakeTeamsRepository
import com.android.carol.sportleagues.domain.model.Teams
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetTeamsResponseUseCaseTest {

    private lateinit var getTeamsUseCase: GetTeamsUseCase
    private lateinit var fakeTeamsRepository: FakeTeamsRepository
    private lateinit var teamsResp : TeamsResp
    private val teamsToInsert = mutableListOf<Teams>()

    @Before
    fun setup(){

        fakeTeamsRepository = FakeTeamsRepository()
        getTeamsUseCase = GetTeamsUseCase(fakeTeamsRepository)

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

    @Test
    fun `If request to network work correctly is Success`() = runTest{
        fakeTeamsRepository.setReturnSuccess(true)
        //request success from repository
        val request = fakeTeamsRepository.getTeamsCountryId(1)

        assertThat(request, `is`(true))
    }

    @Test
    fun `Get Teams from remote`() = runTest {
        val team = Teams("Logo1","Team1")

        val request = fakeTeamsRepository.getTeamsByCountryId(1)
        assertThat(request, IsEqual(team))
    }

    @Test
    fun `Test Teams with getTeam repository`() = runTest{
        val teamsInterface = fakeTeamsRepository.getTeam()
        assertEquals(teamsInterface, teamsToInsert)
    }

}
