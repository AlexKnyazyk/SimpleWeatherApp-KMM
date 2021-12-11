package com.simple.weather.app.android.presentation.navigation

object Routes {

    const val MAIN_TABS = "main_tabs"

    const val HOME = "home"
    const val FAVORITES = "favorites"

    const val SEARCH_LOCATION = "search_location"

    object LocationWeather {
        const val LOCATION_ID = "locationId"
        const val NAME = "location_weather/{$LOCATION_ID}"
        fun route(id: Int) = "location_weather/$id"
    }

    const val SETTINGS = "settings"

}