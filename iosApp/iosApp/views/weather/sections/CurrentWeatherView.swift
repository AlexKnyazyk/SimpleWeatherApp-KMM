//
//  CurrentWeatherView.swift
//  iosApp
//
//  Created by a.knyazuk on 6.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

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
                    .frame(width: 60, height: 60)
            }.padding(.top, 10)
                .padding(.bottom, 10)
            Text("Feels like - 8 C").font(.subheadline)
                .padding(.bottom, 2)
            Text("Current weather").font(.headline)
        }.padding(8)
    }
}

struct CurrentWeatherView_Previews: PreviewProvider {
    static var previews: some View {
        CurrentWeatherView()
    }
}
