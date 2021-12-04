package com.simple.weather.app.android.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentSearchBinding
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.BaseListFragment
import com.simple.weather.app.android.presentation.ui.search.adapter.SearchAdapter
import com.simple.weather.app.android.presentation.ui.search.adapter.SearchItemClickListener
import com.simple.weather.app.android.presentation.ui.search.model.SearchLocationResult
import com.simple.weather.app.android.utils.hideKeyboard
import com.simple.weather.app.android.utils.launchRepeatOnViewLifecycleScope
import com.simple.weather.app.android.utils.view.setOnEditorActionListener
import com.simple.weather.app.domain.model.SearchLocationModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseListFragment<SearchAdapter, FragmentSearchBinding>(), SearchItemClickListener {

    private val viewModel: SearchViewModel by viewModel()

    override val recyclerView: RecyclerView
        get() = binding.searchList

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun createAdapter() = SearchAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInputListeners()
        collectViewModelFlows()
    }

    private fun initInputListeners() = with(binding) {
        searchInput.doOnTextChanged { text, _, _, _ ->
            viewModel.setSearchQuery(text?.toString().orEmpty())
        }
        searchInput.setOnEditorActionListener(EditorInfo.IME_ACTION_SEARCH) {
            activity?.hideKeyboard()
        }
    }

    private fun collectViewModelFlows() = launchRepeatOnViewLifecycleScope {
        launch {
            viewModel.uiState.collect {
                bindUiState(it)
            }
        }
        launch {
            viewModel.events.collect { event ->
                when (event) {
                    SearchScreenEvents.NavigateBack -> findNavController().popBackStack()
                    SearchScreenEvents.ExistedFavoriteMessage -> {
                        Toast.makeText(requireContext(), R.string.location_already_added_to_favorites, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun bindUiState(state: UiState<SearchLocationResult>) = with(binding) {
        loadingProgress.isVisible = state is UiState.Loading

        var isNoSearchResults = false
        if (state is UiState.Data) {
            val result = state.value
            adapter.submitList(result.itemModels)
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
        viewModel.onItemClick(itemModel)
    }
}