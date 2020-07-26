package com.pyteam.vividic.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pyteam.vividic.databinding.ItemSeasonBinding
import com.pyteam.vividic.databinding.ItemTvShowBinding
import com.pyteam.vividic.datasource.entity.tvshows.TvShow
import com.pyteam.vividic.datasource.entity.tvshows.details.Seasons

class SeasonListAdapter(
    val onItemClickListener: OnItemClickListener
): ListAdapter<Seasons, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(ItemSeasonBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ItemViewHolder).bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemSeasonBinding):
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(it,binding.item!!.seasonNumber)
            }
        }
        fun bind(item: Seasons) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, id: String)
    }

    class DiffCallback: DiffUtil.ItemCallback<Seasons>() {

        override fun areContentsTheSame(oldItem: Seasons, newItem: Seasons): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: Seasons, newItem: Seasons): Boolean =
            oldItem == newItem
    }
}