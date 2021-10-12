package com.simple.weather.app.android.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.simple.weather.app.android.databinding.FragmentSearchBinding
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import org.kodein.di.provider

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModelFactory by provider<SearchViewModel.Factory>()
    private val viewModel: SearchViewModel by viewModels { viewModelFactory.invoke() }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}