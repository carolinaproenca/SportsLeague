package com.android.carol.sportleagues.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.carol.sportleagues.R
import com.android.carol.sportleagues.common.season_id
import com.android.carol.sportleagues.databinding.ItemViewSeasonBinding
import com.android.carol.sportleagues.domain.model.SeasonProp
import com.android.carol.sportleagues.presentation.fragment.SeasonFragment

class SeasonAdapter : ListAdapter<SeasonProp, RecyclerView.ViewHolder>(DiffCallback()) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as MyviewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyviewHolder {
        return MyviewHolder.from(parent)
    }


    class MyviewHolder private constructor(private val binding : ItemViewSeasonBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : SeasonProp){
          binding.buttonSeasonNow.text = item.name
            binding.buttonSeasonNow.setOnClickListener { view ->
                var seasonNow : Int = 0
                seasonNow = item.seasonId
                season_id = seasonNow
                view.findNavController().navigate(R.id.action_seasonFragment_to_matchesFragment)
            }
        }
        companion object{
            fun from(parent: ViewGroup) : MyviewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewSeasonBinding.inflate(layoutInflater, parent, false)
                return MyviewHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<SeasonProp>(){
        override fun areItemsTheSame(oldItem: SeasonProp, newItem: SeasonProp): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SeasonProp, newItem: SeasonProp): Boolean {
            return oldItem.name == newItem.name
        }
    }
}