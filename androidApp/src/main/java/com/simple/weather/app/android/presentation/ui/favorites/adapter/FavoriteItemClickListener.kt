package com.simple.weather.app.android.presentation.ui.favorites.adapter

import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationItemUi

interface FavoriteItemClickListener {

    fun onItemClick(itemModel: FavoriteLocationItemUi)
}