package com.simple.weather.app.android.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.simple.weather.app.android.databinding.ItemSearchLocationBinding
import com.simple.weather.app.android.domain.model.SearchLocationModel

class SearchLocationAdapter(
    private val itemClickListener: SearchItemClickListener
) : ListAdapter<SearchLocationModel, SearchLocationViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchLocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchLocationViewHolder(
            ItemSearchLocationBinding.inflate(inflater, parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: SearchLocationViewHolder, position: Int) {
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