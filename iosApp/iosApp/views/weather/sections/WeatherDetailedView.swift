//
//  WeatherDetailedView.swift
//  iosApp
//
//  Created by a.knyazuk on 6.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct WeatherDetailedView : View {
    
    var body: some View {
        VStack {
            WeatherDetailRowView(icon: "wind", text: "Wind", value: "11.2 km/h (E)")
            Divider()
            WeatherDetailRowView(icon: "humidity", text: "Humidity", value: "100%")
            Divider()
            WeatherDetailRowView(icon: "arrow.up.bin", text: "Pressure", value: "1011.1 mbar")
            Divider()
            WeatherDetailRowView(icon: "eye", text: "Visibility", value: "5.0 km")
            Divider()
            WeatherDetailRowView(icon: "sun.max", text: "Index UV", value: "1")
        }.padding(.top, 10)
            .padding(.bottom, 10)
    }
    
    private struct WeatherDetailRowView : View {
        
        private let icon: String
        private let text: String
        private let value: String
        
        init(icon: String, text: String, value: String) {
            self.icon = icon
            self.text = text
            self.value = value
        }
        
        var body: some View {
            HStack {
                Image(systemName: icon)
                Text(text)
                Spacer()
                Text(value)
            }
        }
    }
}

struct WeatherDetailedView_Previews: PreviewProvider {
    static var previews: some View {
        WeatherDetailedView()
    }
}
