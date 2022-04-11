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
import com.android.carol.sportleagues.SportSeasonContainer
import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.databinding.SeasonFragmentBinding
import com.android.carol.sportleagues.domain.model.SeasonProp
import com.android.carol.sportleagues.domain.use_case.season.GetSeasonUseCase
import com.android.carol.sportleagues.presentation.adapters.SeasonAdapter
import com.android.carol.sportleagues.presentation.viewmodel.SeasonViewModel
import kotlinx.android.synthetic.main.item_view_season.*

class SeasonFragment : Fragment() {

    private lateinit var binding : SeasonFragmentBinding
    private lateinit var model : SeasonViewModel
    private val adapter by lazy{ SeasonAdapter() }

    private var seasonNow : Int = 0

    private lateinit var appContainer: AppContainer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SeasonFragmentBinding.inflate(inflater, container, false)

        appContainer = (activity?.application as SportApplication).appContainer
        appContainer.sportSeasonContainer = SportSeasonContainer(appContainer.repositorySeason)

        model = SeasonViewModel(appContainer.repositorySeason)

        binding.seasons.adapter = adapter

        model.getSeasonProperties()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val season = GetSeasonUseCase(appContainer.repositorySeason)
        var seasons = mutableListOf<SeasonProp>()
        model.responseSeasons.observe(this) { item ->
            seasons = season.getSeason(item.data) as MutableList<SeasonProp>
            for(i in item.data.indices) {
                seasonNow = seasons[i].seasonId
            }
            adapter.submitList(seasons)
        }
    }
}