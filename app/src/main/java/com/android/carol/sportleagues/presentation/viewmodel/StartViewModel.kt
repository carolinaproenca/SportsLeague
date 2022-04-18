package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.data.remote.models.LeagueResponse
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.repositories.LeagueRepository
import com.android.carol.sportleagues.domain.use_case.leagues.GetLeagueUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class StartViewModel(repository: LeagueRepository) : ViewModel() {

    private val getLeagueUseCase = GetLeagueUseCase(repository)

    private val _response= MutableLiveData<League>()
    val response: LiveData<League>
        get() = _response

    private val smsError = MutableLiveData<String>()

    fun getLeagueProperties(){
        viewModelScope.launch {
            try{
                _response.value = getLeagueUseCase.getLeagueById(league_id)

            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }
}