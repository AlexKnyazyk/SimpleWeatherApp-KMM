package com.simple.weather.app.data

import platform.Foundation.NSUserDefaults

actual class PreferencesManager {

    private  val userDefaults = NSUserDefaults.standardUserDefaults

    actual fun putInt(key: String, value: Int) {
        userDefaults.setInteger(value.toLong(), key)
    }

    actual fun getInt(key: String, default: Int): Int {
        return userDefaults.objectForKey(key)?.let { userDefaults.integerForKey(key).toInt() } ?: default
    }

    actual fun putBoolean(key: String, value: Boolean) {
        userDefaults.setBool(value, key)
    }

    actual fun getBoolean(key: String, default: Boolean): Boolean {
        return userDefaults.objectForKey(key)?.let { userDefaults.boolForKey(key) } ?: default
    }

    actual fun putString(key: String, value: String?) {
        userDefaults.setObject(value, key)
    }

    actual fun getString(key: String, default: String?): String? {
        return userDefaults.stringForKey(key) ?: default
    }
}