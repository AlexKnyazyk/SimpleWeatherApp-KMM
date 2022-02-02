//
//  WeatherDetailedView.swift
//  iosApp
//
//  Created by a.knyazuk on 6.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct WeatherDetailedView : View {
    
    @State var detailedWeather: DetailedWeatherUi
    @State var settingsUnits: SettingsUnitsUi
    
    var body: some View {
        VStack {
            WeatherDetailRowView(icon: "wind", text: "Wind") {
                HStack {
                    Text(
                        settingsUnits.isDistanceMetric
                        ? "kmh_format \(String(format: "%.1f", detailedWeather.windKph))"
                        : "mph_format \(String(format: "%.1f", detailedWeather.windMph))"
                    )
                    Text("(\(detailedWeather.windDir))")
                }
                
            }
            
            Divider()
            
            WeatherDetailRowView(icon: "humidity", text: "Humidity") {
                Text("\(detailedWeather.humidity)%")
            }
            
            Divider()
            
            WeatherDetailRowView(icon: "arrow.up.bin", text: "Pressure") {
                Text("pressure_mbar_format \(String(format: "%.1f", detailedWeather.pressureMb))")
            }
            
            Divider()
            
            WeatherDetailRowView(icon: "eye", text: "Visibility") {
                Text(
                    settingsUnits.isDistanceMetric
                    ? "km_format \(String(format: "%.1f", detailedWeather.visibilityKm))"
                    : "miles_format \(String(format: "%.1f", detailedWeather.visibilityMiles))"
                )
            }
            
            Divider()
            
            WeatherDetailRowView(icon: "sun.max", text: "Index UV") {
                Text("\(detailedWeather.indexUv)")
            }
        }.padding(.top, 10)
            .padding(.bottom, 10)
    }
    
    private struct WeatherDetailRowView<Content : View> : View {
        
        private let icon: String
        private let text: String
        private let value: () -> Content
        
        init(icon: String, text: String, @ViewBuilder _ value: @escaping () -> Content) {
            self.icon = icon
            self.text = text
            self.value = value
        }
        
        var body: some View {
            HStack {
                Image(systemName: icon)
                Text(text)
                Spacer()
                value()
            }
        }
    }
}

//struct WeatherDetailedView_Previews: PreviewProvider {
//    static var previews: some View {
//        WeatherDetailedView()
//    }
//}
