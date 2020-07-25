package com.pyteam.vividic.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pyteam.vividic.databinding.ItemReviewBinding
import com.pyteam.vividic.databinding.ItemTvShowBinding
import com.pyteam.vividic.datasource.entity.common.reviews.Review
import com.pyteam.vividic.datasource.entity.tvshows.Results

class ReviewListAdapter(
    val onItemClickListener: OnItemClickListener
): ListAdapter<Review, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ItemViewHolder).bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemReviewBinding):
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(it,binding.item!!.id)
            }
        }
        fun bind(item: Review) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, id: String)
    }

    class DiffCallback: DiffUtil.ItemCallback<Review>() {

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem == newItem
    }
}