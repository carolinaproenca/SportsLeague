package com.android.carol.sportleagues.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.data.repositories.FakeTeamsRepository
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.use_case.teams.GetTeamsUseCase
import com.android.carol.sportleagues.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TeamsResponseViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineScopeRule()

    private lateinit var teamsViewModel: TeamsViewModel
    private lateinit var fakeTeamsRepository: FakeTeamsRepository
    private lateinit var getTeamsUseCase: GetTeamsUseCase
    private lateinit var teamsResp : TeamsResp

    @Before
    fun setUp(){

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

        teamsViewModel = TeamsViewModel(fakeTeamsRepository)
    }


    @Test
    fun `Test team with repository and ViewModel updates the livedata correctly`() = runTest{
        val fakeTeam = Teams("123", "Academica")
        fakeTeamsRepository.teams = fakeTeam
        teamsViewModel.getTeamsProperties()
        val result = teamsViewModel.responseTeamsResponse.getOrAwaitValue()
        assertEquals(result,fakeTeam)
    }

}