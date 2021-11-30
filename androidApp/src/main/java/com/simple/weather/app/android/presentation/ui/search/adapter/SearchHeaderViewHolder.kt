package com.simple.weather.app.android.presentation.ui.search.adapter

import com.simple.weather.app.android.databinding.ItemSearchHeaderBinding
import com.simple.weather.app.android.presentation.ui.base.BaseViewHolder
import com.simple.weather.app.android.presentation.ui.search.model.SearchItemUi

class SearchHeaderViewHolder(
    private val binding: ItemSearchHeaderBinding,
) : BaseViewHolder<SearchItemUi.HeaderItem>(binding) {

    override fun bind(item: SearchItemUi.HeaderItem) {
        binding.header.setText(item.headerTextId)
    }
}