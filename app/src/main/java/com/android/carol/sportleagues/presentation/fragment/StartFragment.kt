package com.android.carol.sportleagues.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.carol.sportleagues.*
import com.android.carol.sportleagues.common.country_id
import com.android.carol.sportleagues.common.league_id
import com.android.carol.sportleagues.data.remote.dtoLeagueId.Data
import com.android.carol.sportleagues.databinding.StartFragmentBinding
import com.android.carol.sportleagues.domain.model.League
import com.android.carol.sportleagues.domain.use_case.leagues.GetLeague
import com.android.carol.sportleagues.presentation.viewmodel.StartViewModel

class StartFragment : Fragment() {

    private lateinit var binding : StartFragmentBinding
    private lateinit var model : StartViewModel
    private var countryid1 : Int = 0
    private var leagueid1 : Int = 0
    private var countryid2 : Int = 0
    private var leagueid2 : Int = 0

    private lateinit var appContainer: AppContainer
    private lateinit var getLeague : InterfaceGetLeague
    //private val getLeague = List<GetLeague>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = StartFragmentBinding.inflate(inflater, container, false)

        appContainer = (activity?.application as SportApplication).appContainer

        model = StartViewModel(appContainer.repositoryLeague)

        model.getLeagueProperties()

        binding.buttonPrimeiraliga.setOnClickListener {
            country_id = countryid1
            league_id = leagueid1
            this.findNavController().navigate(R.id.action_startFragment_to_teamsFragment)
        }

        binding.buttonLaliga.setOnClickListener {
            country_id = countryid1
            league_id = leagueid1
            this.findNavController().navigate(R.id.action_startFragment_to_teamsFragment)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //val league = GetLeagueUseCase(appContainer.repositoryLeague)
      //  val data = mutableListOf<Data>()
       // var leagues = mutableListOf<League>()
        getLeague = InterfaceGetLeague()
        model.response.observe(this){ item ->
            countryid1 = item.countryid
            leagueid1  = item.leagueid
            /*leagues = getLeague.getLeague(data) as MutableList<League>
                //league.getLeague(item.data) as MutableList<League>
            countryid1 = leagues[0].countryid
            countryid2 = leagues[1].countryid
            leagueid1 = leagues[0].leagueid
            leagueid2 = leagues[1].leagueid*/
        }
    }
}

class InterfaceGetLeague : GetLeague{
    val data = ArrayList<Data>()
    var league = mutableListOf<League>()

    override val leagues: MutableList<League>
        get() = league
    override val arrayLeagues: ArrayList<Data>
        get() = data

    override fun getLeague(league: List<Data>): List<League> {
        return leagues
    }
}