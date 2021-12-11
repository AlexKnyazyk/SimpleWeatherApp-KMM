package com.simple.weather.app.android.presentation.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.simple.weather.app.android.databinding.ItemFavoriteBinding
import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationItemUi

class FavoritesAdapter(
    private val itemClickListener: FavoriteItemClickListener
) : ListAdapter<FavoriteLocationItemUi, FavoriteItemViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoriteItemViewHolder(
            ItemFavoriteBinding.inflate(inflater, parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: FavoriteItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<FavoriteLocationItemUi>() {
            override fun areItemsTheSame(oldItem: FavoriteLocationItemUi, newItem: FavoriteLocationItemUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteLocationItemUi, newItem: FavoriteLocationItemUi): Boolean {
                return oldItem == newItem
            }
        }
    }
}