package com.simple.weather.app.android.presentation.ui.favorites.adapter

import android.net.Uri
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.ItemFavoriteBinding
import com.simple.weather.app.android.presentation.ui.base.BaseViewHolder
import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationItemUi

class FavoriteItemViewHolder(
    private val binding: ItemFavoriteBinding,
    itemClickListener: FavoriteItemClickListener
) : BaseViewHolder<FavoriteLocationItemUi>(binding) {

    private var itemModel: FavoriteLocationItemUi? = null

    init {
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(requireNotNull(itemModel))
        }
    }

    override fun bind(item: FavoriteLocationItemUi) = with(binding) {
        itemModel = item
        locationName.text = item.name
        locationRegion.text = item.region
        locationCountry.text = item.country

        if (item.tempC != null || item.tempF != null) {
            temperatureValue.text = if (item.isTempMetric) {
                itemView.context.getString(R.string.temperature_c_format, item.tempC)
            } else {
                itemView.context.getString(R.string.temperature_f_format, item.tempF)
            }

            Glide.with(weatherImage)
                .load(Uri.parse(item.weatherConditionIconUrl))
                .into(weatherImage)
            temperatureValue.isVisible = true
            weatherImage.isVisible = true
        } else {
            temperatureValue.isVisible = false
            weatherImage.isVisible = false
        }
    }
}