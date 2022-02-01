//
//  CurrentWeatherView.swift
//  iosApp
//
//  Created by a.knyazuk on 6.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct CurrentWeatherView: View {
    
    @State var currentWeather: CurrentWeatherUi
    @State var settingsUnits: SettingsUnitsUi
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Image(systemName: "location.circle")
                Text(currentWeather.locationName)
            }
            Text(currentWeather.lastUpdated).font(.caption)
                .foregroundColor(.gray)
            HStack {
                Text("temperature_c_format \(currentWeather.tempC)")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                Spacer()
                ImageForUrl(url: URL(string: currentWeather.weatherConditionIconUrl)!, placeholder: { Text("-")})
                    .frame(width: 60, height: 60)
            }.padding(.top, 10)
                .padding(.bottom, 10)
            Text("feels_like_format \(currentWeather.tempFeelsLikeC)").font(.subheadline)
                .padding(.bottom, 2)
            Text(currentWeather.weatherCondition).font(.headline)
        }.padding(8)
    }
}

//struct CurrentWeatherView_Previews: PreviewProvider {
//    static var previews: some View {
//        CurrentWeatherView()
//    }
//}
