package com.simple.weather.app.android.presentation.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view)

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textDashboard.text = it
        }
    }
}