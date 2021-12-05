//
//  HomeWeatherView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct HomeWeatherView: View {
    
    @State private var isShowing = false
    
    var body: some View {
        NavigationView {
            WeatherView()
                .navigationTitle(Text("Simple Weather App"))
        }
    }
}

struct HomeWeatherView_Previews: PreviewProvider {
    static var previews: some View {
        HomeWeatherView()
    }
}
