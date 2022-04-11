package com.android.carol.sportleagues.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.data.remote.MatchesAPI
import com.android.carol.sportleagues.data.remote.dtoMatches.Matches
import com.android.carol.sportleagues.data.remote.dtoMatches.MatchesResp
import com.android.carol.sportleagues.data.repositories.MatchesRepository
import com.android.carol.sportleagues.domain.model.MatchesProp
import com.android.carol.sportleagues.domain.use_case.matches.GetMatchUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class MatchesViewModel(repository: MatchesRepository) : ViewModel() {

   // private val retrofit = MatchesAPI.retrofitServiceMatch

    private val _response= MutableLiveData<Matches>()
    val response: LiveData<Matches>
        get() = _response

    private var match = GetMatchUseCase(repository)
    private var matches = mutableListOf<MatchesProp>()

    private val smsError = MutableLiveData<String>()

    fun getMatchesProperties(){
        viewModelScope.launch {
            try{
                _response.value = match.getProp(season_id)
               // val matchResponse = retrofit.getProperties(season_id = season_id)
                //matches = match.getMatch(matchResponse.data) as MutableList<MatchesProp>
                //_response.value = MatchesResp(matches)

            }catch (e: Exception){
                smsError.value = "Failure+$e"
            }
        }
    }

}
