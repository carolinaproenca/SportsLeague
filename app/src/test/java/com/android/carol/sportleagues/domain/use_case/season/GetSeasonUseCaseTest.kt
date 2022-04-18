package com.android.carol.sportleagues.domain.use_case.season

import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.data.repositories.FakeSeasonsRepository
import com.android.carol.sportleagues.domain.model.Season
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetSeasonUseCaseTest {

    private lateinit var getSeasonUseCase: GetSeasonUseCase
    private lateinit var fakeSeasonsRepository: FakeSeasonsRepository
    private lateinit var seasonResp : SeasonsResp
    //private val data = mutableListOf<Data>()

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
    }


 /*   @Test
    fun testGetLeagueApiFunctional() = runBlocking{
        val season = getSeasonUseCase.getProp(league_id)
        assertEquals(seasonResp.seasons.size, season.data.size)
    }

    @Test
    fun testGetLeagueApiNotFunctional() = runBlocking{
        val season = getSeasonUseCase.getProp(1)
        assertThat(season.data.size, `is`(0))
    }

    @Test
    fun testGetLeagueName() = runBlocking{
        val season = getSeasonUseCase.getSeason(data)
        for(i in 0..season.size){
            assertEquals(seasonResp.seasons[i].name, season[i].name)
        }
    }

    @Test
    fun testGetLeagueSeasonId() = runBlocking{
        val season = getSeasonUseCase.getSeason(data)
        for(i in 0..season.size){
            assertEquals(seasonResp.seasons[i].seasonId, season[i].seasonId)
        }
    }
*/
}