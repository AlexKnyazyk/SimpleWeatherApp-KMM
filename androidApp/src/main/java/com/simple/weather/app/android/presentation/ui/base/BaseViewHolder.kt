package com.simple.weather.app.android.presentation.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<M : Any>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: M)

}