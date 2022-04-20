package com.android.carol.sportleagues.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.data.repositories.FakeLeagueRepository
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.use_case.leagues.GetLeagueUseCase
import com.android.carol.sportleagues.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class StartViewResponseModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineScopeRule()

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
    fun `Test league with repository and ViewModel updates the livedata correctly`() = runTest{
        val fakeLeague = League(123,456, "League1")
        fakeLeagueIdRepository.league = fakeLeague //setUp test
        startViewModel.getLeagueProperties()
        val result = startViewModel.response.getOrAwaitValue()
        assertEquals(result,fakeLeague)
    }

}