package com.android.carol.sportleagues.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.data.repositories.FakeLeagueRepository
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.use_case.leagues.GetLeagueUseCase
import com.android.carol.sportleagues.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class StartViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var startViewModel : StartViewModel
    private lateinit var fakeLeagueIdRepository: FakeLeagueRepository
    private lateinit var getLeagueUseCase: GetLeagueUseCase
    private lateinit var leagues : LeagueResp

    @Before
    fun setup(){
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

        startViewModel = StartViewModel(fakeLeagueIdRepository)
    }

    @Test
    fun `Test the league with repository and ViewModel updates the livedata correctly`() = runTest{
        val fakeLeague = League(98,10, "Portugal")
        fakeLeagueIdRepository.league = fakeLeague //setUp test
        startViewModel.getLeagueProperties()
        val result = startViewModel.response.getOrAwaitValue()
        assertEquals(result,fakeLeague)
    }
}