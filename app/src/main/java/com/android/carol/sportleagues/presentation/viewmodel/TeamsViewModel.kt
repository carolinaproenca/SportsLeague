package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.repositories.TeamsRepository
import com.android.carol.sportleagues.domain.use_case.teams.GetTeamsUseCase
import kotlinx.coroutines.launch

class TeamsViewModel(repository: TeamsRepository) : ViewModel(){

    private val getTeamsUseCase = GetTeamsUseCase(repository)

    private val _responseTeams= MutableLiveData<Teams>()
    val responseTeamsResponse: LiveData<Teams>
        get() = _responseTeams
    private lateinit var data: List<Data>

    private val smsError = MutableLiveData<String>()

    fun getTeamsProperties(){
        viewModelScope.launch {
            try {
                //data = listOf()
                //for(i in data.indices) {
                _responseTeams.value = getTeamsUseCase.getTeamsByCountryId(country_id)
                //}
            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }
}