package com.simple.weather.app.android.presentation.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.simple.weather.app.android.BuildConfig
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.ui.base.RoundedCard
import com.simple.weather.app.android.presentation.ui.base.SingleSelectionDialog
import com.simple.weather.app.android.presentation.ui.base.ToolbarBackIcon
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsDialogUi
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsDistanceUnitUi
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsTemperatureUnitUi
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsUiState
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(navController: NavHostController) {
    val viewModel = getViewModel<SettingsViewModel>()
    val settingsUiState by viewModel.uiState.collectAsState()
    var dialogState by remember { mutableStateOf<SettingsDialogUi>(SettingsDialogUi.None) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_settings)) },
                navigationIcon = {
                    ToolbarBackIcon(onBackClick = { navController.popBackStack() })
                }
            )
        }
    ) {
        SettingsScreenContent(settingsUiState, dialogState = { dialogState = it })
    }

    ShowSettingsDialogContent(
        viewModel,
        settingsUiState,
        dialogState,
        onDismissRequest = { dialogState = SettingsDialogUi.None }
    )
}

@Composable
private fun ShowSettingsDialogContent(
    viewModel: SettingsViewModel,
    settingsUiState: SettingsUiState,
    dialogState: SettingsDialogUi,
    onDismissRequest: () -> Unit
) {
    when (dialogState) {
        SettingsDialogUi.DistanceUnits -> {
            val distanceUnits = SettingsDistanceUnitUi.values()
            SingleSelectionDialog(
                title = stringResource(R.string.settings_speed_or_distance_units),
                optionsList = distanceUnits.map { stringResource(it.textRes) },
                defaultSelected = settingsUiState.distanceUnits.ordinal,
                onSelection = { index ->
                    viewModel.setSpeedOrDistanceUnits(distanceUnits[index])
                },
                onDismissRequest = onDismissRequest
            )
        }
        SettingsDialogUi.TemperatureUnits -> {
            val temperatureUnits = SettingsTemperatureUnitUi.values()
            SingleSelectionDialog(
                title = stringResource(R.string.settings_temperature_units),
                optionsList = temperatureUnits.map { stringResource(it.textRes) },
                defaultSelected = settingsUiState.tempUnits.ordinal,
                onSelection = { index ->
                    viewModel.setTempUnits(temperatureUnits[index])
                },
                onDismissRequest = onDismissRequest
            )
        }
        SettingsDialogUi.None -> {
            // dismiss
        }
    }
}

@Composable
private fun SettingsScreenContent(
    settingsUiState: SettingsUiState,
    dialogState: (SettingsDialogUi) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            SettingsUnitsCard(settingsUiState.tempUnits, settingsUiState.distanceUnits, dialogState)
        }
        item {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(text = BuildConfig.VERSION_NAME)
            }
        }
    }
}

@Composable
private fun SettingsUnitsCard(
    temperatureUnit: SettingsTemperatureUnitUi,
    distanceUnit: SettingsDistanceUnitUi,
    dialogState: (SettingsDialogUi) -> Unit
) {
    RoundedCard {
        Column(modifier = Modifier.fillMaxWidth()) {
            SettingsUnitsRow(
                title = stringResource(R.string.settings_temperature_units),
                value = stringResource(temperatureUnit.textRes),
                onClick = {
                    dialogState(SettingsDialogUi.TemperatureUnits)
                }
            )
            Divider()
            SettingsUnitsRow(
                title = stringResource(R.string.settings_speed_or_distance_units),
                value = stringResource(distanceUnit.textRes),
                onClick = {
                    dialogState(SettingsDialogUi.DistanceUnits)
                }
            )
        }
    }
}

@Composable
private fun SettingsUnitsRow(
    title: String,
    value: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = value,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}
