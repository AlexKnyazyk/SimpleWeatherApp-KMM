package com.simple.weather.app.android.presentation.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.simple.weather.app.android.databinding.ItemFavoriteBinding
import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationModelUi

class FavoritesAdapter(
    private val itemClickListener: FavoriteItemClickListener
) : ListAdapter<FavoriteLocationModelUi, FavoriteItemViewHolder>(DIFF) {

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
        private val DIFF = object : DiffUtil.ItemCallback<FavoriteLocationModelUi>() {
            override fun areItemsTheSame(oldItem: FavoriteLocationModelUi, newItem: FavoriteLocationModelUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteLocationModelUi, newItem: FavoriteLocationModelUi): Boolean {
                return oldItem == newItem
            }
        }
    }
}