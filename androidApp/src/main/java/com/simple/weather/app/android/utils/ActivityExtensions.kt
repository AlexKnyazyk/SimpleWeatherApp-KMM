package com.simple.weather.app.android.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.navigation.findNavController
import com.simple.weather.app.android.R

fun Activity.hideKeyboard() {
    (currentFocus ?: window.decorView).let { view ->
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.findMainNavController() = this.findNavController(R.id.main_nav_host_fragment)