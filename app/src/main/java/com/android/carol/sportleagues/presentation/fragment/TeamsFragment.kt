package com.android.carol.sportleagues.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.carol.sportleagues.AppContainer
import com.android.carol.sportleagues.R
import com.android.carol.sportleagues.SportApplication
import com.android.carol.sportleagues.databinding.TeamsFragmentBinding
import com.android.carol.sportleagues.domain.model.TeamsProp
import com.android.carol.sportleagues.domain.use_case.teams.GetTeamsUseCase
import com.android.carol.sportleagues.presentation.adapters.TeamsAdapter
import com.android.carol.sportleagues.presentation.viewmodel.TeamsViewModel

class TeamsFragment : Fragment() {
    private lateinit var binding: TeamsFragmentBinding
    private lateinit var model : TeamsViewModel
    private val adapter by lazy{ TeamsAdapter() }

    private lateinit var appContainer: AppContainer


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamsFragmentBinding.inflate(inflater, container, false)

        appContainer = (activity?.application as SportApplication).appContainer

        model = TeamsViewModel(appContainer.repositoryTeams)

        binding.teams.adapter = adapter

        model.getTeamsProperties()

        binding.buttonPageGames.setOnClickListener {
            this.findNavController().navigate(R.id.action_teamsFragment_to_seasonFragment)
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        val team = GetTeamsUseCase(appContainer.repositoryTeams)
        var teams = mutableListOf<TeamsProp>()
        model.responseTeams.observe(this){ item ->
            teams = team.getTeam(item.data) as MutableList<TeamsProp>
            adapter.submitList(teams)
        }
    }
}