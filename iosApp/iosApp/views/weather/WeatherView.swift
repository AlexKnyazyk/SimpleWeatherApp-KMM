//
//  WeatherView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct WeatherView: View {
    
    @State private var isShowing = false
    
    init() {
        UITableView.appearance().sectionFooterHeight = 0
    }
    
    var body: some View {
        List {
            Section {
                CurrentWeatherView()
            }
            Section {
                WeatherForecasetView()
                    .listRowInsets(EdgeInsets())
            }
            Section {
                WeatherDetailedView()
            }
            
        }
        .pullToRefresh(isShowing: $isShowing) {
            print("pullToRefresh")
            DispatchQueue.main.asyncAfter(deadline: .now() + 3) {
                print("now + 3")
                self.isShowing = false
            }
        }
        .onChange(of: self.isShowing) { _ in }
    }
}

struct CurrentWeatherView: View {
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Image(systemName: "location.circle")
                Text("Medvezhino")
            }
            Text("Last update: 2021-12-05").font(.caption)
                .foregroundColor(.gray)
            HStack {
                Text("-4 C")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                Spacer()
                ImageForUrl(url: URL(string: "https://cdn.weatherapi.com/weather/64x64/day/329.png")!, placeholder: { Text("-")})
                    .frame(width: 40, height: 20)
            }.padding(.top, 10)
                .padding(.bottom, 10)
            Text("Feels like - 8 C").font(.subheadline)
                .padding(.bottom, 2)
            Text("Current weather").font(.headline)
        }.padding(8)
    }
}

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
            ImageForUrl(url: URL(string: "https://cdn.weatherapi.com/weather/64x64/day/329.png")!, placeholder: { Text("-")})
                .frame(width: 24, height: 16)
                .padding(.top, 10)
                .padding(.bottom, 10)
            Text("12.2 km/h").font(.body)
            Text("(SSW)").font(.body)
        }.frame(width: 120)
    }
}

struct WeatherDetailedView : View {
    
    //TODO
    var body: some View {
        VStack {
            Text("Dateiled")
        }
    }
}

struct WeatherView_Previews: PreviewProvider {
    static var previews: some View {
        WeatherView()
    }
}
