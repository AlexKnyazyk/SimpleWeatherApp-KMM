package com.simple.weather.app.android.utils.view

import android.widget.EditText
import android.widget.TextView

/**
 * @param actionCode Identifier of the action. @see [android.view.inputmethod.EditorInfo] actions
 */
fun EditText.setOnEditorActionListener(actionCode: Int, actionBlock: (TextView) -> Unit) {
    this.setOnEditorActionListener Editor@{ view, actionId, _ ->
        return@Editor (actionId == actionCode).also { isAction ->
            if (isAction) {
                actionBlock(view)
            }
        }
    }
}