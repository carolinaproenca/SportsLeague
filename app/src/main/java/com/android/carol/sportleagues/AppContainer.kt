package com.android.carol.sportleagues

import com.android.carol.sportleagues.data.remote.LeagueIdApi.retrofitServiceLeague
import com.android.carol.sportleagues.data.remote.MatchesAPI.retrofitServiceMatch
import com.android.carol.sportleagues.data.remote.SeasonsAPI.retrofitServiceSeason
import com.android.carol.sportleagues.data.remote.TeamsAPI.retrofitServiceTeams
import com.android.carol.sportleagues.data.repositories.LeagueIdRepository
import com.android.carol.sportleagues.data.repositories.MatchesRepository
import com.android.carol.sportleagues.data.repositories.SeasonsRepository
import com.android.carol.sportleagues.data.repositories.TeamsRepository

class AppContainer {

    val repositoryLeague = LeagueIdRepository(retrofitServiceLeague)
    val repositoryMatch = MatchesRepository(retrofitServiceMatch)
    val repositorySeason = SeasonsRepository(retrofitServiceSeason)
    val repositoryTeams = TeamsRepository(retrofitServiceTeams)

    var sportLeagueContainer : SportLeagueContainer ?=null
    var sportMacthesContainer : SportMatchesContainer ?=null
    var sportSeasonContainer : SportSeasonContainer ?=null
    var sportTeamsContainer : SportTeamsContainer ?=null

}