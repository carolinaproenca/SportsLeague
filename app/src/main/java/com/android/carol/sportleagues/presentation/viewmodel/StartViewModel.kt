package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.data.remote.LeagueIdApi
import com.android.carol.sportleagues.data.remote.dtoLeagueId.LeagueResp
import com.android.carol.sportleagues.data.repositories.LeagueIdRepository
import com.android.carol.sportleagues.domain.model.LeagueProp
import com.android.carol.sportleagues.domain.use_case.leagues.GetLeagueUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class StartViewModel/*(repository: LeagueIdRepository)*/ : ViewModel() {

    private val retrofit = LeagueIdApi.retrofitServiceLeague

    private val _response= MutableLiveData<LeagueResp>()
    val response: LiveData<LeagueResp>
        get() = _response

    private var league = GetLeagueUseCase()
    private var leagues = mutableListOf<LeagueProp>()

    val smsError = MutableLiveData<String>()

    fun getLeagueProperties(){
        viewModelScope.launch {
            try{
                val leagueResponse = retrofit.getProperties(subscribed = true)
                leagues = league.getLeague(listOf(leagueResponse)) as MutableList<LeagueProp>
                _response.value = LeagueResp(leagues)

                /*_response.value = LeagueProp(leagueResponse.data[0].leagueId,leagueResponse.data[0].countryId,
                    leagueResponse.data[0].name,leagueResponse.data[1].leagueId,leagueResponse.data[1].countryId,
                    leagueResponse.data[1].name)*/
            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }
}