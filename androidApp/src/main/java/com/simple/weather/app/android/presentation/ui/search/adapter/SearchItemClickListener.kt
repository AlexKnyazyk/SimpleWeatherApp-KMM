package com.simple.weather.app.android.presentation.ui.search.adapter

import com.simple.weather.app.domain.model.SearchLocationModel

interface SearchItemClickListener {

    fun onItemClick(itemModel: SearchLocationModel)
}