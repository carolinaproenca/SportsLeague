package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.data.remote.dtoMatches.Matches
import com.android.carol.sportleagues.domain.repositories.DMatchesRepository
import com.android.carol.sportleagues.domain.use_case.matches.GetMatchUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class MatchesViewModel(repository: DMatchesRepository) : ViewModel() {

    private val getMatchUseCase = GetMatchUseCase(repository)

    private val _response= MutableLiveData<Matches>()
    val response: LiveData<Matches>
        get() = _response

    private val smsError = MutableLiveData<String>()

    fun getMatchesProperties(){
        viewModelScope.launch {
            try{
                _response.value = getMatchUseCase.getProp(season_id)

            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }

}
