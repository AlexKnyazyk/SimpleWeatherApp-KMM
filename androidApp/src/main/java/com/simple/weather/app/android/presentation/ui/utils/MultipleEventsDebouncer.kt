package com.simple.weather.app.android.presentation.ui.utils

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

interface MultipleEventsDebouncer {

    fun processEvent(event: () -> Unit)

    companion object
}

internal fun MultipleEventsDebouncer.Companion.get(
    deboundeTimeout: Long = 300
): MultipleEventsDebouncer = MultipleEventsDebouncerImpl(deboundeTimeout)

private class MultipleEventsDebouncerImpl(
    private val deboundeTimeout: Long
    ) : MultipleEventsDebouncer {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= deboundeTimeout) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}

fun Modifier.debouncedClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed() {
    val multipleEventsCutter = remember { MultipleEventsDebouncer.get() }
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { multipleEventsCutter.processEvent { onClick() } },
        role = role,
        indication = LocalIndication.current,
        interactionSource = remember { MutableInteractionSource() }
    )
}