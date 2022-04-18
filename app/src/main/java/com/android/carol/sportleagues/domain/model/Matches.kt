package com.android.carol.sportleagues.domain.model

data class Matches (
    val name_team_home : String,
    val name_team_away : String,
    val logo_team_home : String,
    val logo_team_away: String,
    val home_score : Int,
    val away_score : Int
    )