package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.SportSeasonContainer
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.data.remote.dtoSeasons.Seasons
import com.android.carol.sportleagues.data.repositories.SeasonsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SeasonViewModel(repository: SeasonsRepository) : ViewModel() {


    private var getSeasonUseCase: SportSeasonContainer = SportSeasonContainer(repository)

    private val smsError = MutableLiveData<String>()

    private val _responseSeasons= MutableLiveData<Seasons>()
    val responseSeasons: LiveData<Seasons>
        get() = _responseSeasons

    fun getSeasonProperties() {
        viewModelScope.launch {
            try {
                _responseSeasons.value = getSeasonUseCase.seasonUseCase.getProp(league_id)

            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }
}