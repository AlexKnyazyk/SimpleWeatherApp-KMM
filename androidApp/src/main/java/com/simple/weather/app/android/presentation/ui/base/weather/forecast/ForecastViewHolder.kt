package com.simple.weather.app.android.presentation.ui.base.weather.forecast

import android.net.Uri
import android.text.format.DateUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.ItemForecastBinding
import com.simple.weather.app.domain.domain.model.ForecastModel
import java.util.Date

class ForecastViewHolder(
    private val binding: ItemForecastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ForecastModel, isTempMetric: Boolean, isDistanceMetric: Boolean): Unit = with(binding) {
        val date = Date(item.date * 1000)
        val context = binding.root.context
        header.text = when (item) {
            is ForecastModel.Hour -> {
                DateUtils.formatDateTime(context, date.time, DateUtils.FORMAT_SHOW_TIME)
            }
            is ForecastModel.Day -> if (DateUtils.isToday(date.time)) {
                context.getString(R.string.today)
            } else {
                DateUtils.formatDateTime(context, date.time, DateUtils.FORMAT_ABBREV_MONTH or DateUtils.FORMAT_SHOW_DATE)
            }
        }
        temperatureValue.text = if (isTempMetric) {
            context.getString(R.string.temperature_c_format, item.temperatureC)
        } else {
            context.getString(R.string.temperature_f_format, item.temperatureF)
        }
        temperatureRangeValue.isVisible = item is ForecastModel.Day
        if (item is ForecastModel.Day) {
            val minTemp = if (isTempMetric) {
                context.getString(R.string.temperature_c_format, item.temperatureMinC)
            } else {
                context.getString(R.string.temperature_f_format, item.temperatureMinF)
            }
            val maxTemp = if (isTempMetric) {
                context.getString(R.string.temperature_c_format, item.temperatureMaxC)
            } else {
                context.getString(R.string.temperature_f_format, item.temperatureMaxF)
            }
            temperatureRangeValue.text = "$minTemp / $maxTemp"
        }

        val windSpeed = if (isDistanceMetric) {
            context.getString(R.string.kmh_format, item.windSpeedKph)
        } else {
            context.getString(R.string.miles_format, item.windSpeedMph)
        }
        footer.text = windSpeed + if (item is ForecastModel.Hour) "\n(${item.windDir})" else ""

        Glide.with(context)
            .load(Uri.parse(item.iconUrl))
            .into(weatherImage)
    }
}