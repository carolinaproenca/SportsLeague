package com.android.carol.sportleagues.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.data.repositories.FakeSeasonsRepository
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.use_case.season.GetSeasonUseCase
import com.android.carol.sportleagues.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SeasonResponseViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineScopeRule()


    private lateinit var seasonViewModel : SeasonViewModel
    private lateinit var getSeasonUseCase: GetSeasonUseCase
    private lateinit var fakeSeasonsRepository : FakeSeasonsRepository
    private lateinit var seasonResp : SeasonsResp

    @Before
    fun setup(){

        fakeSeasonsRepository = FakeSeasonsRepository()
        getSeasonUseCase = GetSeasonUseCase(fakeSeasonsRepository)

        val seasonsToInsert = mutableListOf<Season>()
        for(i in seasonsToInsert.indices){
            seasonsToInsert.add(
                Season(
                    name = seasonsToInsert[i].name,
                    seasonId = seasonsToInsert[i].seasonId
                )
            )
        }

        seasonsToInsert.shuffle()

        seasonResp = SeasonsResp(seasonsToInsert)
        fakeSeasonsRepository.insertSeasons(seasonResp)

        seasonViewModel = SeasonViewModel(fakeSeasonsRepository)
    }

    @Test
    fun `Test season with repository and ViewModel updates the livedata correctly`() = runTest{
        val fakeSeason = Season("123", 2)
        fakeSeasonsRepository.seasons = fakeSeason
        seasonViewModel.getSeasonProperties()
        val result = seasonViewModel.responseSeasonsResponse.getOrAwaitValue()
        assertEquals(result,fakeSeason)
    }

}
