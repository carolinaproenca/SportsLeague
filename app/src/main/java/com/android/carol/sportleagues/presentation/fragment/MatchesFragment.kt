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
import com.android.carol.sportleagues.SportMatchesContainer
import com.android.carol.sportleagues.databinding.MatchesFragmentBinding
import com.android.carol.sportleagues.domain.model.MatchesProp
import com.android.carol.sportleagues.domain.use_case.matches.GetMatchUseCase
import com.android.carol.sportleagues.presentation.adapters.MatchesAdapter
import com.android.carol.sportleagues.presentation.viewmodel.MatchesViewModel
import kotlinx.android.synthetic.main.item_view_season.*

class MatchesFragment : Fragment() {
    private lateinit var binding : MatchesFragmentBinding
    private lateinit var model : MatchesViewModel
    private val adapter by lazy{ MatchesAdapter() }

    private lateinit var appContainer: AppContainer


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

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val match = GetMatchUseCase(appContainer.repositoryMatch)
        var matches = mutableListOf<MatchesProp>()
        model.response.observe(this){item ->
            matches = match.getMatch(item.data) as MutableList<MatchesProp>
            adapter.submitList(matches)
        }
    }

}