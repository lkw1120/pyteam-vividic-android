package com.pyteam.vividic.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pyteam.vividic.databinding.ItemTvShowBinding
import com.pyteam.vividic.datasource.entity.tvshows.TvShow

class TvShowListAdapter(
    val onItemClickListener: OnItemClickListener
): ListAdapter<TvShow, RecyclerView.ViewHolder>(DiffCallback()) {

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
                onItemClickListener.onItemClick(it,binding.item!!.id)
            }
        }
        fun bind(item: TvShow) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, id: String)
    }

    class DiffCallback: DiffUtil.ItemCallback<TvShow>() {

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
            oldItem == newItem
    }
}