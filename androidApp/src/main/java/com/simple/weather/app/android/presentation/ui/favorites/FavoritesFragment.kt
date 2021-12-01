package com.simple.weather.app.android.presentation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentFavoritesBinding
import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import com.simple.weather.app.android.presentation.ui.details.WeatherDetailsFragment
import com.simple.weather.app.android.presentation.ui.favorites.adapter.FavoriteItemClickListener
import com.simple.weather.app.android.utils.findMainNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(), FavoriteItemClickListener {

    private val viewModel: FavoritesViewModel by viewModel()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFavoritesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        addFavoriteButton.setOnClickListener {
            findMainNavController().navigate(R.id.navigate_to_search)
        }
    }

    override fun onItemClick(itemModel: FavoriteLocationModel) {
        findNavController().navigate(R.id.navigate_to_weather_details, bundleOf(WeatherDetailsFragment.ARG_KEY_NAME to itemModel.name))
    }
}