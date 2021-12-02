package com.simple.weather.app.android.utils.view.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class SwipeToDeleteItemCallback<T>(adapter: ListAdapter<T, *>, private val onDelete: (T) -> Unit) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    private val adapterWeak = WeakReference(adapter)

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.absoluteAdapterPosition
        adapterWeak.get()?.let { adapter ->
            val itemModel = adapter.currentList[position]
            onDelete(itemModel)
        }
    }
}