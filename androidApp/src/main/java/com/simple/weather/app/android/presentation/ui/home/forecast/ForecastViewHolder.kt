package com.simple.weather.app.android.presentation.ui.home.forecast

import android.net.Uri
import android.text.format.DateUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.ItemForecastBinding
import com.simple.weather.app.android.domain.model.ForecastItem
import com.simple.weather.app.android.domain.model.ForecastItemMode
import java.util.Date
import kotlin.math.round

class ForecastViewHolder(
    private val binding: ItemForecastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ForecastItem) = with(binding) {
        val date = Date(item.date * 1000)
        val context = binding.root.context
        header.text = when (item.mode) {
            ForecastItemMode.HOURLY -> {
                DateUtils.formatDateTime(context, date.time, DateUtils.FORMAT_SHOW_TIME)
            }
            ForecastItemMode.DAILY -> if (DateUtils.isToday(date.time)) {
                context.getString(R.string.today)
            } else {
                DateUtils.formatDateTime(context, date.time, DateUtils.FORMAT_ABBREV_MONTH or DateUtils.FORMAT_SHOW_DATE)
            }
        }
        temperatureValue.text = context.getString(R.string.temperature_c_format, round(item.temperature).toInt())
        temperatureRangeValue.isVisible = item.mode == ForecastItemMode.DAILY
        if (item.mode == ForecastItemMode.DAILY) {
            val minTemp = context.getString(R.string.temperature_c_format, round(item.temperatureMin).toInt())
            val maxTemp = context.getString(R.string.temperature_c_format, round(item.temperatureMax).toInt())
            temperatureRangeValue.text = "$minTemp / $maxTemp"
        }

        footer.text = context.getString(R.string.kmh_format, item.windSpeed) +
                if (item.mode == ForecastItemMode.HOURLY) "\n(${item.windDir})" else ""

        Glide.with(context)
            .load(Uri.parse("https:${item.iconUrl}"))
            .into(weatherImage)
    }
}