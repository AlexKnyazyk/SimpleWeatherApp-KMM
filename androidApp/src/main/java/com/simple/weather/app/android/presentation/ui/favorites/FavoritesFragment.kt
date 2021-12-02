package com.simple.weather.app.android.presentation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentFavoritesBinding
import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import com.simple.weather.app.android.presentation.ui.base.BaseListFragment
import com.simple.weather.app.android.presentation.ui.details.WeatherDetailsFragment
import com.simple.weather.app.android.presentation.ui.favorites.adapter.FavoriteItemClickListener
import com.simple.weather.app.android.presentation.ui.favorites.adapter.FavoritesAdapter
import com.simple.weather.app.android.utils.findMainNavController
import com.simple.weather.app.android.utils.launchRepeatOnViewLifecycleScope
import com.simple.weather.app.android.utils.view.recycler.FabHiddenScrollListener
import com.simple.weather.app.android.utils.view.recycler.SpacesItemDecoration
import com.simple.weather.app.android.utils.view.recycler.SwipeToDeleteItemCallback
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseListFragment<FavoritesAdapter, FragmentFavoritesBinding>(), FavoriteItemClickListener {

    private val viewModel: FavoritesViewModel by viewModel()

    override val recyclerView: RecyclerView
        get() = binding.favoriteLocationsList

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFavoritesBinding.inflate(inflater, container, false)

    override fun createAdapter() = FavoritesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        collectViewModelFlows()
    }

    private fun initViews() = with(binding) {
        favoriteLocationsList.apply {
            addItemDecoration(
                SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.default_margin))
            )
            SwipeToDeleteItemCallback(this@FavoritesFragment.adapter) { model ->
                viewModel.deleteFavorite(model)
            }.let { ItemTouchHelper(it).attachToRecyclerView(this) }
            addOnScrollListener(FabHiddenScrollListener(addFavoriteButton))
        }
        addFavoriteButton.setOnClickListener {
            parentFragment?.sharedElementEnterTransition = MaterialContainerTransform()
            val extras = FragmentNavigatorExtras(binding.addFavoriteButton to binding.addFavoriteButton.transitionName)
            findMainNavController().navigate(R.id.navigate_to_search, null, null, extras)
        }
    }

    private fun collectViewModelFlows() = launchRepeatOnViewLifecycleScope {
        viewModel.favoriteLocations.collect {
            bindFavoriteLocations(it)
        }
    }

    private fun bindFavoriteLocations(itemModels: List<FavoriteLocationModel>) = with(binding) {
        adapter.submitList(itemModels)
        hintMessage.isVisible = itemModels.isEmpty()
    }

    override fun onItemClick(itemModel: FavoriteLocationModel) {
        findNavController().navigate(
            R.id.navigate_to_weather_details,
            bundleOf(WeatherDetailsFragment.ARG_KEY_ID to itemModel.id)
        )
    }
}