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
import com.android.carol.sportleagues.data.remote.dtoTeams.Data
import com.android.carol.sportleagues.data.remote.dtoTeams.TeamsResp
import com.android.carol.sportleagues.databinding.TeamsFragmentBinding
import com.android.carol.sportleagues.domain.model.Teams
import com.android.carol.sportleagues.domain.use_case.teams.GetTeams
import com.android.carol.sportleagues.presentation.adapters.TeamsAdapter
import com.android.carol.sportleagues.presentation.viewmodel.TeamsViewModel

class TeamsFragment : Fragment() {
    private lateinit var binding: TeamsFragmentBinding
    private lateinit var model : TeamsViewModel
    private val adapter by lazy{ TeamsAdapter() }

    private lateinit var appContainer: AppContainer
   // private lateinit var getTeams: InterfaceGetTeams
    private lateinit var getTeams: GetTeams
    private var data: List<Data> = listOf()

    private lateinit var tea : TeamsResp


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
        //val team = GetTeamsUseCase(appContainer.repositoryTeams)
        //val data = mutableListOf<Data>()
        var teams = mutableListOf<Teams>()
        getTeams = InterfaceGetTeams(teams)
       // for(i in data.indices) {
            model.responseTeamsResponse.observe(this) { item ->
                tea = TeamsResp(teams)

                teams = getTeams.getTeam(item.logo, item.name) as MutableList<Teams>

                tea = TeamsResp(teams)

                adapter.submitList(teams)

            }
       // }
    }
}

class InterfaceGetTeams(override val teams: MutableList<Teams>) : GetTeams {}
