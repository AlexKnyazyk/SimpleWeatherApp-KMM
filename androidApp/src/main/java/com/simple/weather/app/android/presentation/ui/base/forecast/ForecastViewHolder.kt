package com.simple.weather.app.android.presentation.ui.base.forecast

import android.net.Uri
import android.text.format.DateUtils
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.ItemForecastBinding
import com.simple.weather.app.android.domain.model.ForecastModel
import com.simple.weather.app.android.presentation.ui.base.BaseViewHolder
import java.util.Date

class ForecastViewHolder(
    private val binding: ItemForecastBinding
) : BaseViewHolder<ForecastModel>(binding) {

    override fun bind(item: ForecastModel): Unit = with(binding) {
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
        temperatureValue.text = context.getString(R.string.temperature_c_format, item.temperatureC)
        temperatureRangeValue.isVisible = item is ForecastModel.Day
        if (item is ForecastModel.Day) {
            val minTemp = context.getString(R.string.temperature_c_format, item.temperatureMinC)
            val maxTemp = context.getString(R.string.temperature_c_format, item.temperatureMaxC)
            temperatureRangeValue.text = "$minTemp / $maxTemp"
        }

        footer.text = context.getString(R.string.kmh_format, item.windSpeedKph) +
                if (item is ForecastModel.Hour) "\n(${item.windDir})" else ""

        Glide.with(context)
            .load(Uri.parse(item.iconUrl))
            .into(weatherImage)
    }
}