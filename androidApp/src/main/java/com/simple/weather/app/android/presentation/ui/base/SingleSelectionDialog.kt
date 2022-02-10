package com.simple.weather.app.android.presentation.ui.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun SingleSelectionDialog(
    title: String,
    optionsList: List<String>,
    defaultSelected: Int,
    onSelection: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedOption by remember { mutableStateOf(defaultSelected) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            modifier = Modifier.requiredWidthIn(200.dp, 300.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(16.dp))
                List(optionsList.size) { index ->
                    DialogSelectionItem(
                        index = index,
                        selectedIndex = selectedOption,
                        optionsList = optionsList,
                        onSelection = {
                            selectedOption = it
                            onSelection(it)
                            onDismissRequest()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DialogSelectionItem(
    index: Int,
    selectedIndex: Int,
    optionsList: List<String>,
    onSelection: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onSelection(index) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = index == selectedIndex, onClick = {
            onSelection(index)
        })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = optionsList[index], style = MaterialTheme.typography.body1)
    }
}