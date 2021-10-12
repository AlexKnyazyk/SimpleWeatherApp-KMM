package com.simple.weather.app.android.presentation.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.app.android.databinding.ItemSearchLocationBinding
import com.simple.weather.app.android.domain.model.SearchLocationModel

class SearchLocationViewHolder(
    private val binding: ItemSearchLocationBinding,
    private val itemClickListener: SearchItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private var itemModel: SearchLocationModel? = null

    init {
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(requireNotNull(itemModel))
        }
    }

    fun bind(model: SearchLocationModel) = with(binding) {
        itemModel = model
        locationName.text = model.name
        locationRegion.text = model.region
        locationCountry.text = model.country
    }
}