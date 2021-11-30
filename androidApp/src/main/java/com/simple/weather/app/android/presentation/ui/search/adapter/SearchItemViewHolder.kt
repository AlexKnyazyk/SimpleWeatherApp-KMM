package com.simple.weather.app.android.presentation.ui.search.adapter

import com.simple.weather.app.android.databinding.ItemSearchLocationBinding
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.presentation.ui.base.BaseViewHolder
import com.simple.weather.app.android.presentation.ui.search.model.SearchItemUi

class SearchItemViewHolder(
    private val binding: ItemSearchLocationBinding,
    private val itemClickListener: SearchItemClickListener
) : BaseViewHolder<SearchItemUi.SearchItem>(binding) {

    private var itemModel: SearchLocationModel? = null

    init {
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(requireNotNull(itemModel))
        }
    }

    override fun bind(item: SearchItemUi.SearchItem) = with(binding) {
        val model = item.model
        itemModel = model
        locationName.text = model.name
        locationRegion.text = model.region
        locationCountry.text = model.country
    }
}