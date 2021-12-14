package com.simple.weather.app.android.presentation.ui.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun OutlinedButtonsGroup(
    textList: List<String>,
    selectedIndex: Int,
    onSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        for (index in 0..textList.lastIndex) {
            OutlinedButton(
                modifier = Modifier.weight(1f)
                    .offsetWithZIndexFor(selectedIndex, index),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = if (index == selectedIndex) {
                        MaterialTheme.colors.primary.copy(alpha = 0.15f)
                    } else {
                        MaterialTheme.colors.surface
                    }
                ),
                border = BorderStroke(
                    1.dp,
                    if (index == selectedIndex) {
                        MaterialTheme.colors.primary
                    } else {
                        Color.LightGray
                    }
                ),
                shape = RoundedCornerShape(
                    topStart = if (index == 0) 16.dp else 0.dp,
                    bottomStart = if (index == 0) 16.dp else 0.dp,
                    topEnd = if (index == textList.lastIndex) 16.dp else 0.dp,
                    bottomEnd = if (index == textList.lastIndex) 16.dp else 0.dp,
                ),
                onClick = {
                    if (index != selectedIndex) {
                        onSelected(index)
                    }
                }
            ) {
                Text(
                    text = textList[index].uppercase(),
                    style = MaterialTheme.typography.button,
                )
            }
        }
    }
}

private fun Modifier.offsetWithZIndexFor(selectedIndex: Int, index: Int): Modifier = when (index) {
    0 -> {
        if (selectedIndex == index) {
            this
                .offset(0.dp, 0.dp)
                .zIndex(1f)
        } else {
            this
                .offset(0.dp, 0.dp)
                .zIndex(0f)
        }
    }
    else -> {
        val offset = -1 * index
        if (selectedIndex == index) {
            this
                .offset(offset.dp, 0.dp)
                .zIndex(1f)
        } else {
            this
                .offset(offset.dp, 0.dp)
                .zIndex(0f)
        }
    }
}

@Preview
@Composable
private fun OutlinedButtonsGroup_Preview() {
    OutlinedButtonsGroup(listOf("Daily", "Hourly", "Test"), 1, onSelected = { })
}