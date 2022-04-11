package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.SportTeamsContainer
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.data.remote.dtoTeams.Teams
import com.android.carol.sportleagues.data.repositories.TeamsRepository
import kotlinx.coroutines.launch

class TeamsViewModel(repository: TeamsRepository) : ViewModel(){

    private var getTeamsUseCase: SportTeamsContainer = SportTeamsContainer(repository)

    private val _responseTeams= MutableLiveData<Teams>()
    val responseTeams: LiveData<Teams>
        get() = _responseTeams

    private val smsError = MutableLiveData<String>()

    fun getTeamsProperties(){
        viewModelScope.launch {
            try{
                _responseTeams.value = getTeamsUseCase.teamsUseCase.getProp(country_id)

            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }


}