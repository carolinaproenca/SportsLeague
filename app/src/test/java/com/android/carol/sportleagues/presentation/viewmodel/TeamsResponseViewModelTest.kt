package com.android.carol.sportleagues.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.data.repositories.FakeTeamsRepository
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.use_case.teams.GetTeamsUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TeamsResponseViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

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


}