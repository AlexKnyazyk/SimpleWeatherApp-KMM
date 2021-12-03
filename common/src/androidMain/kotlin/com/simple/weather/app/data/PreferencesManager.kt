package com.simple.weather.app.data

import android.content.Context

actual class PreferencesManager(context: Context) {

    private val preferences = context.getSharedPreferences("SimpleWeatherApp", Context.MODE_PRIVATE)

    actual fun putInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    actual fun getInt(key: String, default: Int): Int {
        return preferences.getInt(key, default)
    }

    actual fun putBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    actual fun getBoolean(key: String, default: Boolean): Boolean {
        return preferences.getBoolean(key, default)
    }

    actual fun putString(key: String, value: String?) {
        preferences.edit().putString(key, value).apply()
    }

    actual fun getString(key: String, default: String?): String? {
        return preferences.getString(key, default)
    }
}