//
//  WeatherForecasetView.swift
//  iosApp
//
//  Created by a.knyazuk on 6.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct WeatherForecasetView: View {
    
    @State var forecastMode: Int = 0
    
    var body: some View {
        VStack {
            Picker("", selection: $forecastMode) {
                Text("Hourly").tag(0)
                Text("Daily").tag(1)
            }
            .pickerStyle(.segmented)
            .padding(10)
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 2) {
                    WeatherForecasetItemView()
                    WeatherForecasetItemView()
                    WeatherForecasetItemView()
                    WeatherForecasetItemView()
                    WeatherForecasetItemView()
                    WeatherForecasetItemView()
                    WeatherForecasetItemView()
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

struct WeatherForecasetView_Previews: PreviewProvider {
    static var previews: some View {
        WeatherForecasetView()
    }
}
