package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.data.remote.SeasonsAPI
import com.android.carol.sportleagues.data.remote.dtoSeasons.Seasons
import com.android.carol.sportleagues.data.remote.dtoSeasons.SeasonsResp
import com.android.carol.sportleagues.data.repositories.SeasonsRepository
import com.android.carol.sportleagues.domain.model.SeasonProp
import com.android.carol.sportleagues.domain.use_case.season.GetSeasonUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class SeasonViewModel(repository: SeasonsRepository) : ViewModel() {

    private val retrofitSeasons = SeasonsAPI.retrofitServiceSeason

    private var season = GetSeasonUseCase(repository)
    private var seasons = mutableListOf<SeasonProp>()
    private val smsError = MutableLiveData<String>()

    private val _responseSeasons= MutableLiveData<Seasons>()
    val responseSeasons: LiveData<Seasons>
        get() = _responseSeasons

    fun getSeasonProperties() {
        viewModelScope.launch {
            try {
                _responseSeasons.value = season.getProp(league_id)
               /* val seasonsResponse = retrofitSeasons.getProperties(league_id = league_id)
                seasons = season.getSeason(seasonsResponse.data) as MutableList<SeasonProp>
                _responseSeasons.value = SeasonsResp(seasons)*/
            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }
}