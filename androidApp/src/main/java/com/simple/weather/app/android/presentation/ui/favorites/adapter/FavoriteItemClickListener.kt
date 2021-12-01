package com.simple.weather.app.android.presentation.ui.favorites.adapter

import com.simple.weather.app.android.domain.model.FavoriteLocationModel

interface FavoriteItemClickListener {

    fun onItemClick(itemModel: FavoriteLocationModel)
}