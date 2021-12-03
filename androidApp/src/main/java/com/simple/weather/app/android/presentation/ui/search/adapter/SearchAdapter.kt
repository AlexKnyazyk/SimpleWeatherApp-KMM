package com.simple.weather.app.android.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.simple.weather.app.android.databinding.ItemSearchLocationBinding
import com.simple.weather.app.domain.domain.model.SearchLocationModel

class SearchAdapter(
    private val itemClickListener: SearchItemClickListener
) : ListAdapter<SearchLocationModel, SearchItemViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchItemViewHolder(
            ItemSearchLocationBinding.inflate(inflater, parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        private val DIFF = object : DiffUtil.ItemCallback<SearchLocationModel>() {
            override fun areItemsTheSame(
                oldItem: SearchLocationModel,
                newItem: SearchLocationModel
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: SearchLocationModel,
                newItem: SearchLocationModel
            ) = oldItem == newItem
        }
    }
}