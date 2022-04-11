package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.data.remote.TeamsAPI
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.data.repositories.TeamsRepository
import com.android.carol.sportleagues.domain.model.TeamsProp
import com.android.carol.sportleagues.domain.use_case.teams.GetTeamsUseCase
import kotlinx.coroutines.launch

class TeamsViewModel(repository: TeamsRepository) : ViewModel(){

    private val retrofitTeams = TeamsAPI.retrofitServiceTeams
    private val _responseTeams= MutableLiveData<TeamsResp>()
    val responseTeams: LiveData<TeamsResp>
        get() = _responseTeams

    private var team = GetTeamsUseCase(repository)
    private var teams = mutableListOf<TeamsProp>()
    private val smsError = MutableLiveData<String>()

    fun getTeamsProperties(){
        viewModelScope.launch {
            try{
                val teamsResponse = retrofitTeams.getProperties(country_id = country_id)
                teams = team.getTeam(teamsResponse.data) as MutableList<TeamsProp>
                _responseTeams.value = TeamsResp(teams)
            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }


}