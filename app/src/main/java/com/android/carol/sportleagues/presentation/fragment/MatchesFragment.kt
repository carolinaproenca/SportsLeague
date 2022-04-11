package com.android.carol.sportleagues.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.carol.sportleagues.AppContainer
import com.android.carol.sportleagues.R
import com.android.carol.sportleagues.SportApplication
import com.android.carol.sportleagues.SportMatchesContainer
import com.android.carol.sportleagues.data.remote.dtoMatches.Matches
import com.android.carol.sportleagues.databinding.MatchesFragmentBinding
import com.android.carol.sportleagues.domain.model.MatchesProp
import com.android.carol.sportleagues.domain.use_case.matches.GetMatchUseCase
import com.android.carol.sportleagues.presentation.adapters.MatchesAdapter
import com.android.carol.sportleagues.presentation.viewmodel.MatchesViewModel

class MatchesFragment : Fragment() {
    private lateinit var binding : MatchesFragmentBinding
    private lateinit var model : MatchesViewModel
    private val adapter by lazy{ MatchesAdapter() }

    private lateinit var appContainer: AppContainer

    private var match = GetMatchUseCase(appContainer.repositoryMatch)
    private var matches = mutableListOf<MatchesProp>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MatchesFragmentBinding.inflate(inflater, container, false)

        appContainer = (activity?.application as SportApplication).appContainer
        appContainer.sportMacthesContainer = SportMatchesContainer(appContainer.repositoryMatch)

        model = MatchesViewModel(appContainer.repositoryMatch)

        binding.resumeMatches.adapter = adapter

        model.getMatchesProperties()

        binding.buttonSeasonNow.setOnClickListener {
            this.findNavController().navigate(R.id.action_matchesFragment_self)
        }
        binding.buttonSeason1.setOnClickListener {
            this.findNavController().navigate(R.id.action_matchesFragment_self)
        }

        binding.buttonSeason2.setOnClickListener {
            this.findNavController().navigate(R.id.action_matchesFragment_self)
        }

        binding.buttonSeason3.setOnClickListener {
            this.findNavController().navigate(R.id.action_matchesFragment_self)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        model.response.observe(this){item ->
            matches = match.getMatch(item.data) as MutableList<MatchesProp>
            adapter.submitList(matches)
        }
    }

}