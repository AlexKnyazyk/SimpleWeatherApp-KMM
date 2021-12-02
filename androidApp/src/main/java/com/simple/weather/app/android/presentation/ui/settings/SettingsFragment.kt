package com.simple.weather.app.android.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialSharedAxis
import com.simple.weather.app.android.BuildConfig
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentSettingsBinding
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsDistanceUnitUi
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsTemperatureUnitUi
import com.simple.weather.app.android.utils.launchRepeatOnViewLifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSettingsBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        collectViewModelFlows()
    }

    private fun initViews() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        settingTempUnits.setOnClickListener { showTemperatureUnitsSelectionDialog() }
        settingDistanceUnits.setOnClickListener { showDistanceUnitsSelectionDialog() }
        buildInfo.text = BuildConfig.VERSION_NAME
    }

    private fun collectViewModelFlows() = launchRepeatOnViewLifecycleScope {
        launch {
            viewModel.uiState.collect { state ->
                binding.settingTempUnitsValue.setText(state.tempUnits.uiValue)
                binding.settingDistanceUnitsValue.setText(state.distanceUnits.uiValue)
            }
        }
    }

    private fun showTemperatureUnitsSelectionDialog() {
        val tempUnitValues = SettingsTemperatureUnitUi.values()
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_temperature_units)
            .setSingleChoiceItems(
                tempUnitValues.map { getString(it.uiValue) }.toTypedArray(),
                viewModel.uiState.value.tempUnits.ordinal,
            ) { dialog, which ->
                viewModel.setTempUnits(tempUnitValues[which])
                dialog.dismiss()
            }
            .show()
    }

    private fun showDistanceUnitsSelectionDialog() {
        val distanceUnitValues = SettingsDistanceUnitUi.values()
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_speed_or_distance_units)
            .setSingleChoiceItems(
                distanceUnitValues.map { getString(it.uiValue) }.toTypedArray(),
                viewModel.uiState.value.distanceUnits.ordinal,
            ) { dialog, which ->
                viewModel.setSpeedOrDistanceUnits(distanceUnitValues[which])
                dialog.dismiss()
            }
            .show()
    }
}