package com.android.carol.sportleagues.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.carol.sportleagues.AppContainer
import com.android.carol.sportleagues.SportApplication
import com.android.carol.sportleagues.databinding.MatchesFragmentBinding
import com.android.carol.sportleagues.domain.model.Matches
import com.android.carol.sportleagues.domain.use_case.matches.GetMatchUseCase
import com.android.carol.sportleagues.presentation.adapters.MatchesAdapter
import com.android.carol.sportleagues.presentation.viewmodel.MatchesViewModel
import com.android.carol.sportleagues.domain.use_case.matches.GetMatches as GetMatches

class MatchesFragment : Fragment() {
    private lateinit var binding : MatchesFragmentBinding
    private lateinit var model : MatchesViewModel
    private val adapter by lazy{ MatchesAdapter() }
    private lateinit var getMatches: GetMatches

    private lateinit var appContainer: AppContainer


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MatchesFragmentBinding.inflate(inflater, container, false)

        appContainer = (activity?.application as SportApplication).appContainer

        model = MatchesViewModel(appContainer.repositoryMatch)

        binding.resumeMatches.adapter = adapter

        model.getMatchesProperties()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //val match = GetMatchUseCase(appContainer.repositoryMatch)
        var matches = mutableListOf<Matches>()
        model.response.observe(this){//item ->
            matches = getMatches.matches
            //getMatch(item.data) as MutableList<Matches>
               // match.getMatch(item.data) as MutableList<Matches>
           // matches = getMatches.matches
            adapter.submitList(matches)
        }
    }

}