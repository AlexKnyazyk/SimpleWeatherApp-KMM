package com.simple.weather.app.android.presentation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentFavoritesBinding
import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import com.simple.weather.app.android.presentation.ui.details.WeatherDetailsFragment
import com.simple.weather.app.android.presentation.ui.favorites.adapter.FavoriteItemClickListener
import com.simple.weather.app.android.presentation.ui.favorites.adapter.FavoritesAdapter
import com.simple.weather.app.android.utils.findMainNavController
import com.simple.weather.app.android.utils.launchRepeatOnViewLifecycleScope
import com.simple.weather.app.android.utils.view.recycler.SpacesItemDecoration
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(), FavoriteItemClickListener {

    private val viewModel: FavoritesViewModel by viewModel()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFavoritesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        collectViewModelFlows()
    }

    private fun initViews() = with(binding) {
        binding.favoriteLocationsList.apply {
            adapter = FavoritesAdapter(this@FavoritesFragment)
            addItemDecoration(
                SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.default_margin))
            )
        }
        addFavoriteButton.setOnClickListener {
            findMainNavController().navigate(R.id.navigate_to_search)
        }
    }

    private fun collectViewModelFlows() = launchRepeatOnViewLifecycleScope {
        viewModel.favoriteLocations.collect {
            bindFavoriteLocations(it)
        }
    }

    private fun bindFavoriteLocations(itemModels: List<FavoriteLocationModel>) = with(binding) {
        (favoriteLocationsList.adapter as FavoritesAdapter).submitList(itemModels)
        hintMessage.isVisible = itemModels.isEmpty()
    }

    override fun onItemClick(itemModel: FavoriteLocationModel) {
        findNavController().navigate(
            R.id.navigate_to_weather_details,
            bundleOf(WeatherDetailsFragment.ARG_KEY_NAME to itemModel.name)
        )
    }
}