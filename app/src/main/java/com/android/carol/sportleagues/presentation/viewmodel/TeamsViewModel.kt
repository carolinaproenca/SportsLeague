package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.data.remote.dtoTeams.Teams
import com.android.carol.sportleagues.domain.repositories.DTeamsRepository
import com.android.carol.sportleagues.domain.use_case.teams.GetTeamsUseCase
import kotlinx.coroutines.launch

class TeamsViewModel(repository: DTeamsRepository) : ViewModel(){

    private val getTeamsUseCase = GetTeamsUseCase(repository)

    private val _responseTeams= MutableLiveData<Teams>()
    val responseTeams: LiveData<Teams>
        get() = _responseTeams

    private val smsError = MutableLiveData<String>()

    fun getTeamsProperties(){
        viewModelScope.launch {
            try{
                _responseTeams.value = getTeamsUseCase.getProp(country_id)

            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }


}