package com.pyteam.vividic.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

const val LINEAR_LAYOUT_VERTICAL = 0
const val LINEAR_LAYOUT_HORIZONTAL = 1
const val GRID_LAYOUT = 2

class ItemDecorator(
    private val width: Int,
    private val height: Int,
    private val extraMargin: Int,
    private val layoutType: Int,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        outRect.apply {
            val firstPosition = 0
            val lastPosition = parent.adapter!!.itemCount - 1
            when(layoutType) {
                LINEAR_LAYOUT_VERTICAL -> {
                    when (position) {
                        firstPosition -> {
                            top = extraMargin
                            bottom = height
                        }
                        lastPosition -> {
                            top = 0
                            bottom = extraMargin
                        }
                        else -> {
                            top = 0
                            bottom = height
                        }
                    }
                    left = width
                    right = height
                }
                LINEAR_LAYOUT_HORIZONTAL -> {
                    when (position) {
                        firstPosition -> {
                            left = extraMargin
                            right = width
                        }
                        lastPosition -> {
                            left = 0
                            right = extraMargin
                        }
                        else -> {
                            left = 0
                            right = width
                        }
                    }
                    top = height
                    bottom = height
                }
                GRID_LAYOUT -> {
                    when (position%spanCount) {
                        0 -> {
                            left = extraMargin
                            right = width
                        }
                        spanCount - 1 -> {
                            left = 0
                            right = extraMargin
                        }
                        else -> {
                            left = 0
                            right = width
                        }
                    }
                    top = height
                    bottom = height
                }
            }
        }
    }
}