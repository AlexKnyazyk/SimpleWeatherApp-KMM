package com.simple.weather.app.android.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.simple.weather.app.android.databinding.ItemSearchFavoriteBinding
import com.simple.weather.app.android.databinding.ItemSearchHeaderBinding
import com.simple.weather.app.android.databinding.ItemSearchLocationBinding
import com.simple.weather.app.android.presentation.ui.base.BaseViewHolder
import com.simple.weather.app.android.presentation.ui.search.model.SearchItemUi

class SearchAdapter(
    private val itemClickListener: SearchItemClickListener
) : ListAdapter<SearchItemUi, BaseViewHolder<out SearchItemUi>>(DIFF) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SearchItemUi.HeaderItem -> ITEM_HEADER
            is SearchItemUi.FavoriteItem -> ITEM_FAVORITE
            is SearchItemUi.SearchItem -> ITEM_SEARCH
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<out SearchItemUi> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_HEADER -> SearchHeaderViewHolder(
                ItemSearchHeaderBinding.inflate(inflater, parent, false)
            )
            ITEM_FAVORITE -> SearchFavoriteViewHolder(
                ItemSearchFavoriteBinding.inflate(inflater, parent, false),
                itemClickListener
            )
            ITEM_SEARCH -> SearchItemViewHolder(
                ItemSearchLocationBinding.inflate(inflater, parent, false),
                itemClickListener
            )
            else -> throw IllegalArgumentException("Unknown viewType=$viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out SearchItemUi>, position: Int) {
        when (val item = getItem(position)) {
            is SearchItemUi.HeaderItem -> (holder as SearchHeaderViewHolder).bind(item)
            is SearchItemUi.FavoriteItem -> (holder as SearchFavoriteViewHolder).bind(item)
            is SearchItemUi.SearchItem -> (holder as SearchItemViewHolder).bind(item)
        }
    }

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_FAVORITE = 1
        private const val ITEM_SEARCH = 2

        private val DIFF = object : DiffUtil.ItemCallback<SearchItemUi>() {
            override fun areItemsTheSame(
                oldItem: SearchItemUi,
                newItem: SearchItemUi
            ) = when {
                oldItem is SearchItemUi.HeaderItem && newItem is SearchItemUi.HeaderItem -> oldItem.headerTextId == newItem.headerTextId
                oldItem is SearchItemUi.FavoriteItem && newItem is SearchItemUi.FavoriteItem -> oldItem.model.id == newItem.model.id
                oldItem is SearchItemUi.SearchItem && newItem is SearchItemUi.SearchItem -> oldItem.model.id == newItem.model.id
                else -> false
            }

            override fun areContentsTheSame(
                oldItem: SearchItemUi,
                newItem: SearchItemUi
            ) = oldItem == newItem
        }
    }
}