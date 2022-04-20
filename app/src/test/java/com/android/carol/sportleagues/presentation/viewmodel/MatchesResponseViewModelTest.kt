package com.android.carol.sportleagues.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.data.repositories.FakeMatchesRepository
import com.android.carol.sportleagues.domain.model.Matches
import com.android.carol.sportleagues.domain.use_case.matches.GetMatchUseCase
import com.android.carol.sportleagues.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MatchesResponseViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineScopeRule()

    private lateinit var matchesViewModel: MatchesViewModel
    private lateinit var getMatchUseCase: GetMatchUseCase
    private lateinit var fakeMatchesRepository: FakeMatchesRepository
    private lateinit var matches : MatchesResp
    //private val data = mutableListOf<Data>()


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
    fun `Test match with repository and ViewModel updates the livedata correctly`() = runTest{
        val fakeMatch = Matches("Benfica", "Cadima", "123", "456", 5,1)
        fakeMatchesRepository.matches = fakeMatch
        matchesViewModel.getMatchesProperties()
        val result = matchesViewModel.response.getOrAwaitValue()
        assertEquals(result,fakeMatch)
    }

}