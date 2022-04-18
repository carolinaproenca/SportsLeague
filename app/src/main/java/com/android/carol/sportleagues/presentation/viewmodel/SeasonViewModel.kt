package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.data.remote.models.SeasonsResponse
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.repositories.SeasonsRepository
import com.android.carol.sportleagues.domain.use_case.season.GetSeasonUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class SeasonViewModel(repository: SeasonsRepository) : ViewModel() {

    private val getSeasonUseCase = GetSeasonUseCase(repository)

    private val smsError = MutableLiveData<String>()

    private val _responseSeasons= MutableLiveData<Season>()
    val responseSeasonsResponse: LiveData<Season>
        get() = _responseSeasons

    fun getSeasonProperties() {
        viewModelScope.launch {
            try {
                _responseSeasons.value = getSeasonUseCase.getSeasonByLeagueId(league_id)

            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }
}