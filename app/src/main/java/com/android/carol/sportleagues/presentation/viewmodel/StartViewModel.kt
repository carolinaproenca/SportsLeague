package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.data.remote.LeagueIdApi
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueId
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.data.repositories.LeagueIdRepository
import com.android.carol.sportleagues.domain.model.LeagueProp
import com.android.carol.sportleagues.domain.use_case.leagues.GetLeagueUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class StartViewModel(repository: LeagueIdRepository) : ViewModel() {

    private val retrofit = LeagueIdApi.retrofitServiceLeague

    private val _response= MutableLiveData<LeagueId>()
    val response: LiveData<LeagueId>
        get() = _response

    private var league = GetLeagueUseCase(repository)
    private var leagues = mutableListOf<LeagueProp>()

    private val smsError = MutableLiveData<String>()

    fun getLeagueProperties(){
        viewModelScope.launch {
            try{
                _response.value = league.getProp(true)
                /*val leagueResponse = retrofit.getProperties(subscribed = true)
                leagues = league.getLeague(leagueResponse.data) as MutableList<LeagueProp>
                _response.value = LeagueResp(leagues)*/

            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }
}