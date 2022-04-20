package com.android.carol.sportleagues.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.carol.sportleagues.AppContainer
import com.android.carol.sportleagues.SportApplication
import com.android.carol.sportleagues.databinding.SeasonFragmentBinding
import com.android.carol.sportleagues.domain.model.Season
import com.android.carol.sportleagues.domain.use_case.season.GetSeason
import com.android.carol.sportleagues.presentation.adapters.SeasonAdapter
import com.android.carol.sportleagues.presentation.viewmodel.SeasonViewModel

class SeasonFragment : Fragment() {

    private lateinit var binding : SeasonFragmentBinding
    private lateinit var model : SeasonViewModel
    private val adapter by lazy{ SeasonAdapter() }
    private lateinit var getSeason: GetSeason

    var name : String = "test"
    var seasonId : Int = 0

    private lateinit var appContainer: AppContainer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SeasonFragmentBinding.inflate(inflater, container, false)

        appContainer = (activity?.application as SportApplication).appContainer

        model = SeasonViewModel(appContainer.repositorySeason)

        binding.seasons.adapter = adapter

        model.getSeasonProperties()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
       // val season = GetSeasonUseCase(appContainer.repositorySeason)
        var seasons = mutableListOf<Season>()
        getSeason = InterfaceGetSeason(seasons)
        model.responseSeasonsResponse.observe(this) { item ->
            //name = item.name
            //seasonId = item.seasonId
            //seasons = getSeason.seasons
            seasons = getSeason.getSeason(item.name,item.seasonId) as MutableList<Season>

            adapter.submitList(seasons)
        }
    }
}

class InterfaceGetSeason(override val seasons: MutableList<Season>) : GetSeason{}