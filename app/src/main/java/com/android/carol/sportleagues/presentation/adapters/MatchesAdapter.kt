package com.android.carol.sportleagues.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.carol.sportleagues.R
import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.databinding.ItemViewMatchesBinding
import com.android.carol.sportleagues.domain.model.MatchesProp
import com.bumptech.glide.Glide

class MatchesAdapter : ListAdapter<MatchesProp, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as MyviewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyviewHolder {
        return MyviewHolder.from(parent)
    }


    class MyviewHolder private constructor(private val binding : ItemViewMatchesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : MatchesProp){
            binding.team1Name.text = item.name_team_home
            binding.team2Name.text = item.name_team_away
            binding.numberGoalsFirstTeam.text = item.home_score.toString()
            binding.numberGoalsSecondTeam.text = item.away_score.toString()

            val urlTeamHome = item.logo_team_home
            val image = binding.team1Logo

            Glide.with(image)
                .load(urlTeamHome)
                .into(image)

            val urlTeamAway = item.logo_team_away
            val imageAway = binding.team2Logo
            Glide.with(imageAway)
                .load(urlTeamAway)
                .into(imageAway)
        }
        companion object{
            fun from(parent: ViewGroup) : MyviewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewMatchesBinding.inflate(layoutInflater, parent, false)
                return MyviewHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MatchesProp>(){
        override fun areItemsTheSame(oldItem: MatchesProp, newItem: MatchesProp): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MatchesProp, newItem: MatchesProp): Boolean {
            return oldItem.name_team_home == newItem.name_team_home
        }
    }
}