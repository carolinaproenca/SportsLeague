package com.android.carol.sportleagues.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.carol.sportleagues.R
import com.android.carol.sportleagues.databinding.TeamsFragmentBinding
import com.android.carol.sportleagues.presentation.adapters.TeamsAdapter
import com.android.carol.sportleagues.presentation.viewmodel.TeamsViewModel

class TeamsFragment : Fragment() {
    private lateinit var binding: TeamsFragmentBinding
    private lateinit var model : TeamsViewModel
    private val adapter by lazy{ TeamsAdapter() }
    //private lateinit var appContainer: AppContainer
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamsFragmentBinding.inflate(inflater, container, false)
        model = ViewModelProvider(this).get(TeamsViewModel::class.java)
        /*appContainer = (activity?.application as SportApplication).appContainer
        appContainer.sportTeamsContainer = SportTeamsContainer(appContainer.repositoryTeams)
        model = TeamsViewModel(appContainer.repositoryTeams)*/

        binding.teams.adapter = adapter

        model.getTeamsProperties()

        binding.buttonPageGames.setOnClickListener {
            this.findNavController().navigate(R.id.action_teamsFragment_to_seasonFragment)
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        model.responseTeams.observe(this){ item ->
            adapter.submitList(item.teams)
        }
    }
}