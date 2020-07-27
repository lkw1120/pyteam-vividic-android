package com.pyteam.vividic.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
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
                            bottom = 0
                        }
                        lastPosition -> {
                            top = height
                            bottom = extraMargin
                        }
                        else -> {
                            top = height
                            bottom = 0
                        }
                    }
                    left = width
                    right = width
                }
                LINEAR_LAYOUT_HORIZONTAL -> {
                    when (position) {
                        firstPosition -> {
                            left = extraMargin
                            right = 0
                        }
                        lastPosition -> {
                            left = width
                            right = extraMargin
                        }
                        else -> {
                            left = width
                            right = 0
                        }
                    }
                    top = height
                    bottom = height
                }
                GRID_LAYOUT -> {
                    when((view.layoutParams as GridLayoutManager.LayoutParams).spanIndex) {
                        0 -> {
                            left = extraMargin
                            right = 0
                        }
                        spanCount-1 -> {
                            left = 0
                            right = extraMargin
                        }
                        else -> {
                            left = width
                            right = 0
                        }
                    }
                    top = height
                    bottom = height
                }
            }
        }
    }
}