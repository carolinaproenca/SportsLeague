package com.android.carol.sportleagues.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.data.repositories.FakeMatchesRepository
import com.android.carol.sportleagues.domain.model.Matches
import com.android.carol.sportleagues.domain.use_case.matches.GetMatchUseCase
import com.android.carol.sportleagues.getOrAwaitValue
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class MatchesResponseViewModelTest{

    private lateinit var matchesViewModel: MatchesViewModel
    private lateinit var getMatchUseCase: GetMatchUseCase
    private lateinit var fakeMatchesRepository: FakeMatchesRepository
    private lateinit var matches : MatchesResp
    //private val data = mutableListOf<Data>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        fakeMatchesRepository = FakeMatchesRepository()

        getMatchUseCase = GetMatchUseCase(fakeMatchesRepository)

        val matchesToInsert = mutableListOf<Matches>()
        for(i in matchesToInsert.indices){
            matchesToInsert.add(
                Matches(
                    name_team_home = matchesToInsert[i].name_team_home,
                    name_team_away = matchesToInsert[i].name_team_away,
                    logo_team_home = matchesToInsert[i].logo_team_home,
                    logo_team_away = matchesToInsert[i].logo_team_away,
                    home_score = matchesToInsert[i].home_score,
                    away_score = matchesToInsert[i].away_score
                )
            )
        }

        matchesToInsert.shuffle()

        matches = MatchesResp(matchesToInsert)
        fakeMatchesRepository.insertMatches(matches)

        matchesViewModel = MatchesViewModel(fakeMatchesRepository)
    }

    @Test
    fun testGetResponse() {
       // setOf(matchesViewModel.response)
        val value = matchesViewModel.response.getOrAwaitValue()
        //assertEquals(matches.matches.size, value.data.size)
       // assertThat(value, not(nullValue()))

      // val value = matchesViewModel.getMatchesProperties()
        assertEquals(matchesViewModel.response.value, value)
        assertThat(matchesViewModel.response.getOrAwaitValue(), `is`(true))
    }


}