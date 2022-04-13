package com.android.carol.sportleagues.domain.use_case.season

import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.data.repositories.FakeSeasonsRepository
import com.android.carol.sportleagues.domain.model.SeasonProp
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class GetSeasonUseCaseTest {

    private lateinit var getSeasonUseCase: GetSeasonUseCase
    private lateinit var fakeSeasonsRepository: FakeSeasonsRepository
    private lateinit var seasonResp : SeasonsResp

    @Before
    fun setup(){
        fakeSeasonsRepository = FakeSeasonsRepository()
        getSeasonUseCase = GetSeasonUseCase(fakeSeasonsRepository)

        val seasonsToInsert = mutableListOf<SeasonProp>()
        for(i in seasonsToInsert.indices){
            seasonsToInsert.add(
                SeasonProp(
                    name = seasonsToInsert[i].name,
                    seasonId = seasonsToInsert[i].seasonId
                )
            )
        }

        seasonsToInsert.shuffle()

        seasonResp = SeasonsResp(seasonsToInsert)
        fakeSeasonsRepository.insertSeasons(seasonResp)
    }


    @Test
    fun testGetLeagueApiFunctional() = runBlocking{
        val season = getSeasonUseCase.getProp(league_id)
        assertEquals(seasonResp.seasons.size, season.data.size)
    }

    @Test
    fun testGetLeagueApiNotFunctional() = runBlocking{
        val season = getSeasonUseCase.getProp(1)
        assertThat(season.data.size, `is`(0))
    }

}