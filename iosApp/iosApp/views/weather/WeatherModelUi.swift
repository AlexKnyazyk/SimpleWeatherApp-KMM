//
//  WeatherModelUi.swift
//  iosApp
//
//  Created by a.knyazuk on 15.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import common

struct WeatherModelUi {
    let currentWeather: CurrentWeatherUi
//        let forecastWeather: ForecastWeatherUi
    let detailedWeather: DetailedWeatherUi
    let settingsUnits: SettingsUnitsUi
    
    init(weatherModel: WeatherModel, settingsUnitsModel: SettingsUnitsModel) {
        self.currentWeather = CurrentWeatherUi(
            locationName: weatherModel.locationName,
            locationCountry: weatherModel.locationCountry,
            lastUpdated: weatherModel.lastUpdated,
            tempC: Int(weatherModel.tempC),
            tempF: Int(weatherModel.tempF),
            tempFeelsLikeC: Int(weatherModel.tempFeelsLikeC),
            tempFeelsLikeF: Int(weatherModel.tempFeelsLikeF),
            weatherCondition: weatherModel.weatherCondition,
            weatherConditionIconUrl: weatherModel.weatherConditionIconUrl
        )
        self.detailedWeather = DetailedWeatherUi(
            windKph: Double(weatherModel.windKph),
            windMph: Double(weatherModel.windMph),
            windDir: weatherModel.windDir,
            humidity: Int(weatherModel.humidity),
            pressureMb: Double(weatherModel.pressureMb),
            visibilityKm: Double(weatherModel.visibilityKm),
            visibilityMiles: Double(weatherModel.visibilityMiles),
            indexUv: Int(weatherModel.indexUv)
        )
        self.settingsUnits = SettingsUnitsUi(
            isTempMetric: settingsUnitsModel.isTempMetric,
            isDistanceMetric: settingsUnitsModel.isDistanceMetric
        )
    }
}
