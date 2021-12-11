package com.simple.weather.app.android.presentation.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simple.weather.app.android.R

@Composable
fun ErrorContentWithTryAgain(
    error: Throwable,
    tryAgainAction: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Text(
                error.message ?: stringResource(R.string.error_unknown),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(onClick = tryAgainAction) {
                Text(
                    stringResource(R.string.action_try_again),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

@Preview
@Composable
private fun ErrorContentWithTryAgain_Preview() {
    ErrorContentWithTryAgain(Throwable("Some error")) { }
}