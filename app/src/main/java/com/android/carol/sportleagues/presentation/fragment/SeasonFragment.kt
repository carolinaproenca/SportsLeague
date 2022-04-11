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
import com.android.carol.sportleagues.SportSeasonContainer
import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.databinding.SeasonFragmentBinding
import com.android.carol.sportleagues.presentation.viewmodel.SeasonViewModel

class SeasonFragment : Fragment() {

    private lateinit var binding : SeasonFragmentBinding
    private lateinit var model : SeasonViewModel
    private var seasonNow : Int = 0
    private var season1 : Int = 0
    private var season2 : Int = 0
    private var season3 : Int = 0

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

        model.getSeasonProperties()

        binding.buttonSeasonNow.setOnClickListener {
            season_id = seasonNow
            this.findNavController().navigate(R.id.action_seasonFragment_to_matchesFragment)
        }
        binding.buttonSeason1.setOnClickListener {
            season_id = season1
            this.findNavController().navigate(R.id.action_seasonFragment_to_matchesFragment)
        }
        binding.buttonSeason2.setOnClickListener {
            season_id = season2
            this.findNavController().navigate(R.id.action_seasonFragment_to_matchesFragment)
        }
        binding.buttonSeason3.setOnClickListener {
            season_id = season3
            this.findNavController().navigate(R.id.action_seasonFragment_to_matchesFragment)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        model.responseSeasons.observe(this) { item ->
           // for (i in item.seasons.indices) {
                season1 = item.seasons[0].seasonId
                season2 = item.seasons[1].seasonId
                season3 = item.seasons[2].seasonId
                seasonNow = item.seasons[3].seasonId
          //  }
        }
    }
}