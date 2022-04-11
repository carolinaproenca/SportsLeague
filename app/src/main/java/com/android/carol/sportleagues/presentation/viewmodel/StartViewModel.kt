package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.SportLeagueContainer
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueId
import com.android.carol.sportleagues.data.repositories.LeagueIdRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class StartViewModel(repository: LeagueIdRepository) : ViewModel() {

    private var getLeagueUseCase: SportLeagueContainer = SportLeagueContainer(repository)

    private val _response= MutableLiveData<LeagueId>()
    val response: LiveData<LeagueId>
        get() = _response

    private val smsError = MutableLiveData<String>()

    fun getLeagueProperties(){
        viewModelScope.launch {
            try{
                _response.value = getLeagueUseCase.getLeagueUseCase.getProp(true)

            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }
}