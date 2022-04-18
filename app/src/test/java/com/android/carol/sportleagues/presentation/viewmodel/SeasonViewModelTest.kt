package com.android.carol.sportleagues.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.data.repositories.FakeSeasonsRepository
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.use_case.season.GetSeasonUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SeasonViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


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

}