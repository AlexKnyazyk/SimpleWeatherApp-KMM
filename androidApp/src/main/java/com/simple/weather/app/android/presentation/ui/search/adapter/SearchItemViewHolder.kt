package com.simple.weather.app.android.presentation.ui.search.adapter

import com.simple.weather.app.android.databinding.ItemSearchLocationBinding
import com.simple.weather.app.android.presentation.ui.base.BaseViewHolder
import com.simple.weather.app.domain.model.SearchLocationModel

class SearchItemViewHolder(
    private val binding: ItemSearchLocationBinding,
    private val itemClickListener: SearchItemClickListener
) : BaseViewHolder<SearchLocationModel>(binding) {

    private var itemModel: SearchLocationModel? = null

    init {
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(requireNotNull(itemModel))
        }
    }

    override fun bind(item: SearchLocationModel) = with(binding) {
        itemModel = item
        locationName.text = item.name
        locationRegion.text = item.region
        locationCountry.text = item.country
    }
}