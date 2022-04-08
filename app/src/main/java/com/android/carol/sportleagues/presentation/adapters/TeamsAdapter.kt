package com.android.carol.sportleagues.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.carol.sportleagues.databinding.ItemViewTeamsBinding
import com.android.carol.sportleagues.domain.model.TeamsProp
import com.bumptech.glide.Glide

class TeamsAdapter : ListAdapter<TeamsProp, RecyclerView.ViewHolder>(DiffCallback()) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as MyviewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyviewHolder {
        return MyviewHolder.from(parent)
    }

    class MyviewHolder private constructor(private val binding : ItemViewTeamsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : TeamsProp){
            binding.teamName.text = item.name

            val url = item.logo
            val image = binding.logo
            Glide.with(image).load(url).into(image)

        }
        companion object{
            fun from(parent: ViewGroup) : MyviewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewTeamsBinding.inflate(layoutInflater, parent, false)
                return MyviewHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TeamsProp>(){
        override fun areItemsTheSame(oldItem: TeamsProp, newItem: TeamsProp): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TeamsProp, newItem: TeamsProp): Boolean {
            return oldItem == newItem
        }
    }
}