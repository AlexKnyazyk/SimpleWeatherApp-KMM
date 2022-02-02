//
//  WeatherForecasetView.swift
//  iosApp
//
//  Created by a.knyazuk on 6.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct WeatherForecasetView: View {
    
    @State var forecastWeather: ForecastWeatherUi
    @State var settingsUnits: SettingsUnitsUi
    @State var forecastMode: ForecastModeUi = .hourly
    private let dateFormatter = DateFormatter()
    private let timeFormatter = DateFormatter()
    
    init(forecastWeather: ForecastWeatherUi, settingsUnits: SettingsUnitsUi) {
        self.forecastWeather = forecastWeather
        self.settingsUnits = settingsUnits
        dateFormatter.dateFormat = "dd MMM"
        timeFormatter.dateFormat = "HH:mm"
    }
    
    var body: some View {
        VStack {
            Picker("", selection: $forecastMode) {
                Text("Hourly").tag(ForecastModeUi.hourly)
                Text("Daily").tag(ForecastModeUi.daily)
            }
            .pickerStyle(.segmented)
            .padding(10)
            
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 2) {
                    switch forecastMode {
                    case .daily:
                        
                        ForEach(forecastWeather.forecastDaily, id: \.dateMillis) { day in
                            VStack {
                                let date = Date(timeIntervalSince1970: TimeInterval(day.dateMillis))
                                if (Calendar.current.isDateInToday(date)) {
                                    Text("today").font(.caption)
                                } else {
                                    Text(date, formatter: dateFormatter).font(.caption)
                                }
                                Text(settingsUnits.isTempMetric ? "temperature_c_format \(day.temperatureC)" : "temperature_f_format \(day.temperatureF)")
                                    .font(.largeTitle)
                                HStack {
                                    Text(settingsUnits.isTempMetric ? "temperature_c_format \(day.temperatureMinC)" : "temperature_f_format \(day.temperatureMinF)").font(.caption)
                                    Text("/").font(.caption)
                                    Text(settingsUnits.isTempMetric ? "temperature_c_format \(day.temperatureMaxC)" : "temperature_f_format \(day.temperatureMaxF)").font(.caption)
                                }
                                ImageForUrl(url: URL(string: day.iconUrl)!, placeholder: { Text("-")})
                                    .frame(width: 32, height: 32)
                                    .padding(.top, 4)
                                    .padding(.bottom, 4)
                                let windSpeed = String(format: "%.1f", settingsUnits.isTempMetric ? day.windSpeedKph : day.windSpeedMph)
                                Text(settingsUnits.isTempMetric ? "kmh_format \(windSpeed)" : "mph_format \(windSpeed)").font(.body)
                            }.frame(width: 120)
                        }
                    case .hourly:
                        ForEach(forecastWeather.forecastHourly, id: \.dateMillis) { hour in
                            VStack {
                                let time = Date(timeIntervalSince1970: TimeInterval(hour.dateMillis))
                                Text(time, formatter: timeFormatter).font(.caption)
                                Text(settingsUnits.isTempMetric ? "temperature_c_format \(hour.temperatureC)" : "temperature_f_format \(hour.temperatureF)")
                                    .font(.largeTitle)
                                ImageForUrl(url: URL(string: hour.iconUrl)!, placeholder: { Text("-")})
                                    .frame(width: 32, height: 32)
                                    .padding(.top, 4)
                                    .padding(.bottom, 4)
                                let windSpeed = String(format: "%.1f", settingsUnits.isTempMetric ? hour.windSpeedKph : hour.windSpeedMph)
                                Text(settingsUnits.isTempMetric ? "kmh_format \(windSpeed)" : "mph_format \(windSpeed)").font(.body)
                                Text("(\(hour.windDir))").font(.body)
                            }.frame(width: 120)
                        }
                    }
                }
            }
            .padding(.bottom, 10)
        }
    }
}

struct WeatherForecasetItemView : View {
    
    var body: some View {
        VStack {
            Text("00:00").font(.caption)
            Text("-3 C").font(.largeTitle)
            ImageForUrl(url: URL(string: "https://cdn.weatherapi.com/weather/64x64/day/329.png")!,
                        placeholder: { Text("-")})
                .frame(width: 32, height: 32)
                .padding(.top, 4)
                .padding(.bottom, 4)
            Text("12.2 km/h").font(.body)
            Text("(SSW)").font(.body)
        }.frame(width: 120)
    }
}

//struct WeatherForecasetView_Previews: PreviewProvider {
//    static var previews: some View {
//        WeatherForecasetView()
//    }
//}
