package com.pyteam.vividic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pyteam.vividic.databinding.ItemTvShowBinding
import com.pyteam.vividic.datasource.entity.tvshows.Results
import com.pyteam.vividic.ui.MainFragmentDirections

class TvShowListAdapter: ListAdapter<Results, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(ItemTvShowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ItemViewHolder).bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemTvShowBinding):
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToTvShowDetailsFragement(binding.item!!.id))
            }
        }
        fun bind(item: Results) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Results>() {

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean =
            oldItem == newItem
    }
}