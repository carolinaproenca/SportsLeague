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

import com.android.carol.sportleagues.presentation.viewmodel.StartViewModel

class StartFragment : Fragment() {

    private lateinit var binding : StartFragmentBinding
    private lateinit var model : StartViewModel

    private lateinit var appContainer: AppContainer

    private var countryId : Int = 0
    private var leagueId : Int = 0


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
            country_id = countryId
            league_id = leagueId
            this.findNavController().navigate(R.id.action_startFragment_to_teamsFragment)
        }

        binding.buttonLaliga.setOnClickListener {
            country_id = countryId
            league_id = leagueId
            this.findNavController().navigate(R.id.action_startFragment_to_teamsFragment)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        var leagues = mutableListOf<League>()

        model.response.observe(this){ item ->
            leagues = appContainer.repositoryLeague.getLeague() as MutableList<League>
            countryId = item.countryid
             leagueId = item.leagueid
        }
    }
}
