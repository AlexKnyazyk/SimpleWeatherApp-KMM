package com.simple.weather.app.android.presentation.ui.search.model

import androidx.annotation.StringRes
import com.simple.weather.app.android.domain.model.SearchLocationModel

sealed class SearchItemUi {
    data class HeaderItem(@StringRes val headerTextId: Int) : SearchItemUi()
    data class FavoriteItem(val model: SearchLocationModel) : SearchItemUi()
    data class SearchItem(val model: SearchLocationModel) : SearchItemUi()
}
