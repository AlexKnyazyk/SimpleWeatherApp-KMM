package com.simple.weather.app.android.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentSearchBinding
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.presentation.model.SearchLocationResult
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import com.simple.weather.app.android.presentation.ui.search.adapter.SearchItemClickListener
import com.simple.weather.app.android.presentation.ui.search.adapter.SearchLocationAdapter
import com.simple.weather.app.android.utils.hideKeyboard
import com.simple.weather.app.android.utils.launchRepeatOnViewLifecycleScope
import com.simple.weather.app.android.utils.view.setOnEditorActionListener
import kotlinx.coroutines.flow.collect
import org.kodein.di.provider

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchItemClickListener {

    private val viewModelFactory by provider<SearchViewModel.Factory>()
    private val viewModel: SearchViewModel by viewModels { viewModelFactory() }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchResultsList()
        setupInputListeners()
        collectViewModelFlows()
    }

    private fun setupSearchResultsList() {
        binding.searchList.apply {
            adapter = SearchLocationAdapter(this@SearchFragment)
        }
    }

    private fun setupInputListeners() = with(binding) {
        searchInput.doOnTextChanged { text, _, _, _ ->
            viewModel.setSearchQuery(text?.toString().orEmpty())
        }
        searchInput.setOnEditorActionListener(EditorInfo.IME_ACTION_SEARCH) {
            activity?.hideKeyboard()
        }
    }

    private fun collectViewModelFlows() = launchRepeatOnViewLifecycleScope {
        viewModel.uiState.collect {
            bindUiState(it)
        }
    }

    private fun bindUiState(state: UiState<SearchLocationResult>) = with(binding) {
        loadingProgress.isVisible = state is UiState.Loading

        var isNoSearchResults = false
        if (state is UiState.Data) {
            val result = state.value
            (searchList.adapter as SearchLocationAdapter).submitList(result.itemModels)
            if (result.hasSearchQuery && result.itemModels.isEmpty()) {
                isNoSearchResults = true
                errorMessage.setText(R.string.no_search_results)
            }
        }
        if (state is UiState.Error) {
            errorMessage.text = getString(R.string.error_generic_format, state.uiError.message)
        }

        errorMessage.isVisible = state is UiState.Error || isNoSearchResults
    }

    override fun onItemClick(itemModel: SearchLocationModel) {
        // TODO
    }

    override fun onDestroyView() {
        binding.searchList.adapter = null
        super.onDestroyView()
    }
}