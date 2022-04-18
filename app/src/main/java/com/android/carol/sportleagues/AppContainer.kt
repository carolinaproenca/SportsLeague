package com.android.carol.sportleagues

import com.android.carol.sportleagues.data.remote.LeagueIdApi.retrofitServiceLeague
import com.android.carol.sportleagues.data.remote.MatchesAPI.retrofitServiceMatch
import com.android.carol.sportleagues.data.remote.SeasonsAPI.retrofitServiceSeason
import com.android.carol.sportleagues.data.remote.TeamsAPI.retrofitServiceTeams
import com.android.carol.sportleagues.data.repositories.LeagueRepositoryImpl
import com.android.carol.sportleagues.data.repositories.MatchesRepositoryImpl
import com.android.carol.sportleagues.data.repositories.SeasonsRepositoryImpl
import com.android.carol.sportleagues.data.repositories.TeamsRepositoryImpl

class AppContainer {

    val repositoryLeague = LeagueRepositoryImpl(retrofitServiceLeague)
    val repositoryMatch = MatchesRepositoryImpl(retrofitServiceMatch)
    val repositorySeason = SeasonsRepositoryImpl(retrofitServiceSeason)
    val repositoryTeams = TeamsRepositoryImpl(retrofitServiceTeams)

}