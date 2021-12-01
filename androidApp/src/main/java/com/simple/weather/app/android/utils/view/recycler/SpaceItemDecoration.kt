package com.simple.weather.app.android.utils.view.recycler

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(
    private val itemSideSpace: Int,
    private val spaceBetweenItems: Int = itemSideSpace / 2,
) : RecyclerView.ItemDecoration() {

    constructor(
        context: Context,
        @DimenRes itemSideSpaceRes: Int,
        @DimenRes spaceBetweenItemsRes: Int = itemSideSpaceRes
    ) : this(
        context.resources.getDimensionPixelSize(itemSideSpaceRes),
        context.resources.getDimensionPixelSize(spaceBetweenItemsRes)
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemsCount = parent.adapter?.itemCount ?: 0
        if ((itemSideSpace == 0 && spaceBetweenItems == 0) || itemsCount == 0) return
        val position = parent.getChildAdapterPosition(view)
        outRect.left = itemSideSpace
        outRect.right = itemSideSpace
        when {
            itemsCount == 1 -> {
                outRect.top = itemSideSpace
                outRect.bottom = itemSideSpace
            }
            position == 0 -> {
                outRect.top = itemSideSpace
                outRect.bottom = spaceBetweenItems
            }
            position == itemsCount - 1 -> {
                outRect.top = spaceBetweenItems
                outRect.bottom = itemSideSpace
            }
            else -> {
                outRect.top = spaceBetweenItems
                outRect.bottom = spaceBetweenItems
            }
        }
    }
}