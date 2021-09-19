package com.simple.weather.app.android.presentation.ui.home.forecast

import android.net.Uri
import android.text.format.DateUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.ItemForecastBinding
import com.simple.weather.app.android.domain.model.ForecastModel
import java.util.Date

class ForecastViewHolder(
    private val binding: ItemForecastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: ForecastModel) = with(binding) {
        val date = Date(model.date * 1000)
        val context = binding.root.context
        header.text = when (model) {
            is ForecastModel.Hour -> {
                DateUtils.formatDateTime(context, date.time, DateUtils.FORMAT_SHOW_TIME)
            }
            is ForecastModel.Day -> if (DateUtils.isToday(date.time)) {
                context.getString(R.string.today)
            } else {
                DateUtils.formatDateTime(context, date.time, DateUtils.FORMAT_ABBREV_MONTH or DateUtils.FORMAT_SHOW_DATE)
            }
        }
        temperatureValue.text = context.getString(R.string.temperature_c_format, model.temperatureC)
        temperatureRangeValue.isVisible = model is ForecastModel.Day
        if (model is ForecastModel.Day) {
            val minTemp = context.getString(R.string.temperature_c_format, model.temperatureMinC)
            val maxTemp = context.getString(R.string.temperature_c_format, model.temperatureMaxC)
            temperatureRangeValue.text = "$minTemp / $maxTemp"
        }

        footer.text = context.getString(R.string.kmh_format, model.windSpeedKph) +
                if (model is ForecastModel.Hour) "\n(${model.windDir})" else ""

        Glide.with(context)
            .load(Uri.parse(model.iconUrl))
            .into(weatherImage)
    }
}