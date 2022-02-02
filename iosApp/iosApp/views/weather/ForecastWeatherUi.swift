//
//  ForecastWeatherUi.swift
//  iosApp
//
//  Created by Alexey Knyazuk on 02.02.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import common

struct ForecastWeatherUi {
    let forecastDaily: [DailyUi]
    let forecastHourly: [HourlyUi]
    
    init(daily: [ForecastModel.Day], hourly: [ForecastModel.Hour]) {
        self.forecastDaily = daily.map({ day in
            DailyUi(
                dateMillis: Int64(day.dateMillis),
                temperatureC: Int(day.temperatureC),
                temperatureF: Int(day.temperatureF),
                temperatureMaxC: Int(day.temperatureMaxC),
                temperatureMaxF: Int(day.temperatureMaxF),
                temperatureMinC: Int(day.temperatureMinC),
                temperatureMinF: Int(day.temperatureMinF),
                iconUrl: day.iconUrl,
                windSpeedKph: Double(day.windSpeedKph),
                windSpeedMph: Double(day.windSpeedMph)
            )
        })
        self.forecastHourly = hourly.map({ hour in
            HourlyUi(
                dateMillis: Int64(hour.dateMillis),
                temperatureC: Int(hour.temperatureC),
                temperatureF: Int(hour.temperatureF),
                iconUrl: hour.iconUrl,
                windSpeedKph: Double(hour.windSpeedKph),
                windSpeedMph: Double(hour.windSpeedMph),
                windDir: hour.windDir
            )
        })
    }
    
    struct HourlyUi {
        let dateMillis: Int64
        let temperatureC: Int
        let temperatureF: Int
        let iconUrl: String
        let windSpeedKph: Double
        let windSpeedMph: Double
        let windDir: String
    }
    
    struct DailyUi {
        let dateMillis: Int64
        let temperatureC: Int
        let temperatureF: Int
        let temperatureMaxC: Int
        let temperatureMaxF: Int
        let temperatureMinC: Int
        let temperatureMinF: Int
        let iconUrl: String
        let windSpeedKph: Double
        let windSpeedMph: Double
    }
}
