package com.pyteam.vividic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pyteam.vividic.databinding.ItemCrewBinding
import com.pyteam.vividic.datasource.entity.common.Crew

class CrewListAdapter: ListAdapter<Crew, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            ItemViewHolder(ItemCrewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ItemViewHolder).bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemCrewBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {

            }
        }
        fun bind(item: Crew) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

  class DiffCallback: DiffUtil.ItemCallback<Crew>() {

      override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean =
          oldItem.id == newItem.id

      override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean =
          oldItem == newItem
  }
}