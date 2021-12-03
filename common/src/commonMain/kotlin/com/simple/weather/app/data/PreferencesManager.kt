package com.simple.weather.app.data

expect class PreferencesManager {

    fun putInt(key: String, value: Int)

    fun getInt(key: String, default: Int = 0): Int

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, default: Boolean): Boolean

    fun putString(key: String, value: String?)

    fun getString(key: String, default: String?): String?

}