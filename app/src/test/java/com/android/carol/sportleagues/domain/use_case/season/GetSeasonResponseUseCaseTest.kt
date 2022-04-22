package com.android.carol.sportleagues.domain.use_case.season

import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.data.repositories.FakeSeasonsRepository
import com.android.carol.sportleagues.domain.model.Season
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetSeasonUseCaseTest {

    private lateinit var getSeasonUseCase: GetSeasonUseCase
    private lateinit var fakeSeasonsRepository: FakeSeasonsRepository
    private lateinit var seasonResp : SeasonsResp
    private val seasonsToInsert = mutableListOf<Season>()

    @Before
    fun setup(){
        fakeSeasonsRepository = FakeSeasonsRepository()
        getSeasonUseCase = GetSeasonUseCase(fakeSeasonsRepository)

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

    }

    @Test
    fun `If request to network work correctly is Success`() = runTest{
        fakeSeasonsRepository.setReturnSuccess(true)
        //request success from repository
        val request = fakeSeasonsRepository.getSeasonId(1)

        assertThat(request, CoreMatchers.`is`(true))
    }

    @Test
    fun `Get Seasons from remote`() = runTest {
        val season = Season("Season1", 1)
        val request = fakeSeasonsRepository.getSeasonByLeagueId(1)
        assertThat(request, IsEqual(season))
    }

    @Test
    fun `Test Season with getSeason repository`() = runTest{
        val seasonInterface =fakeSeasonsRepository.getSeason()
        assertEquals(seasonInterface, seasonsToInsert)
    }

}
