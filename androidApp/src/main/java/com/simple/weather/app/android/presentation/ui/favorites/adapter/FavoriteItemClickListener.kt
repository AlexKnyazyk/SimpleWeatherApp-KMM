package com.simple.weather.app.android.presentation.ui.favorites.adapter

import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationModelUi

interface FavoriteItemClickListener {

    fun onItemClick(itemModel: FavoriteLocationModelUi)
}