package com.android.carol.sportleagues.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.carol.sportleagues.*
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.databinding.StartFragmentBinding
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.use_case.leagues.GetLeague
import com.android.carol.sportleagues.presentation.viewmodel.StartViewModel

class StartFragment : Fragment() {

    private lateinit var binding : StartFragmentBinding
    private lateinit var model : StartViewModel

    private lateinit var appContainer: AppContainer
    private lateinit var getLeague : GetLeague


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = StartFragmentBinding.inflate(inflater, container, false)

        appContainer = (activity?.application as SportApplication).appContainer

        model = StartViewModel(appContainer.repositoryLeague)

        model.getLeagueProperties()

        binding.buttonPrimeiraliga.setOnClickListener {
            this.findNavController().navigate(R.id.action_startFragment_to_teamsFragment)
        }

        binding.buttonLaliga.setOnClickListener {
            this.findNavController().navigate(R.id.action_startFragment_to_teamsFragment)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //val league = GetLeagueUseCase(appContainer.repositoryLeague)
        var leagues = mutableListOf<League>()
        getLeague = InterfaceGetLeague(leagues)
        model.response.observe(this){ item ->
            leagues = getLeague.getLeague(item.leagueid, item.countryid, item.name) as MutableList<League>
            country_id = item.countryid
            league_id  = item.leagueid
        }
    }
}

class InterfaceGetLeague(override val leagues: MutableList<League>) : GetLeague{

}