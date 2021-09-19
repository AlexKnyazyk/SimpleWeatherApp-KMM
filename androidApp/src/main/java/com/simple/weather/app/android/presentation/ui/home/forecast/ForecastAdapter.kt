package com.simple.weather.app.android.presentation.ui.home.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.weather.app.android.databinding.ItemForecastBinding
import com.simple.weather.app.android.domain.model.ForecastModel
import kotlin.properties.Delegates

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {

    var itemModels: List<ForecastModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ForecastViewHolder(
            ItemForecastBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(itemModels[position])
    }

    override fun getItemCount(): Int = itemModels.size
}