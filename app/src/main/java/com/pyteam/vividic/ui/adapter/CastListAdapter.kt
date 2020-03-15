package com.pyteam.vividic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pyteam.vividic.databinding.ItemCastBinding
import com.pyteam.vividic.datasource.entity.common.Cast

class CastListAdapter: ListAdapter<Cast, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            ItemViewHolder(ItemCastBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ItemViewHolder).bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemCastBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {

            }
        }
        fun bind(item: Cast) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

  class DiffCallback: DiffUtil.ItemCallback<Cast>() {

      override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean =
          oldItem.id == newItem.id

      override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean =
          oldItem == newItem
  }
}