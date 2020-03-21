package com.pyteam.vividic.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListItemDecoration(
    private val width: Int,
    private val height: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        outRect.apply {
            left = if(position == 0) width else 0
            right = width
            top = height
            bottom = height
        }
    }
}