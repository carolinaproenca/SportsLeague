package com.android.carol.sportleagues.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.carol.sportleagues.R
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.databinding.StartFragmentBinding
import com.android.carol.sportleagues.presentation.viewmodel.StartViewModel

class StartFragment : Fragment() {

    private lateinit var binding : StartFragmentBinding
    private lateinit var model : StartViewModel
    private var countryid1 : Int = 0
    private var leagueid1 : Int = 0
    private var countryid2 : Int = 0
    private var leagueid2 : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartFragmentBinding.inflate(inflater, container, false)

        model = ViewModelProvider(this).get(StartViewModel::class.java)

        model.getLeagueProperties()

        binding.buttonPrimeiraliga.setOnClickListener {
            country_id = countryid1
            league_id = leagueid1
            this.findNavController().navigate(R.id.action_startFragment_to_teamsFragment)
        }

        binding.buttonLaliga.setOnClickListener {
            country_id = countryid2
            league_id = leagueid2
            this.findNavController().navigate(R.id.action_startFragment_to_teamsFragment)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        model.response.observe(this){ item ->
            /*countryid1 = item.countryid1
            countryid2 = item.countryid2
            leagueid1 = item.leagueid1
            leagueid2 = item.leagueid2*/
            for (i in item.league.indices) {
                countryid1 = item.league[0].countryid1
                countryid2 = item.league[1].countryid2
                leagueid1 = item.league[0].leagueid1
                leagueid2 = item.league[1].leagueid2
            }
        }
    }
}