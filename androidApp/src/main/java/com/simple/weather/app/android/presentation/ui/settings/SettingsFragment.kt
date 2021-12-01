package com.simple.weather.app.android.presentation.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.simple.weather.app.android.databinding.FragmentSettingsBinding
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSettingsBinding.inflate(inflater, container, false)

}