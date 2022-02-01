//
//  HomeWeatherView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct HomeWeatherView: View {
    
    private let viewModel: WeatherViewModel = WeatherViewModel(
        getWeatherUseCase: iOSApp.di.getWeatherUseCase(),
        settingsRepository: iOSApp.di.getSettingsRepository()
    )
    
    @State private var isShowing = false
    
    @Binding public var rootNavigation: String?
    
    var body: some View {
        NavigationView {
            WeatherView(viewModel: viewModel)
                .navigationTitle(Text("Simple Weather"))
                .navigationBarItems(
                    trailing: Button(action: { rootNavigation = "Settings" }) { Image(systemName: "gearshape.fill")
                })
        }
    }
}

//struct HomeWeatherView_Previews: PreviewProvider {
//    static var previews: some View {
//        HomeWeatherView($false)
//    }
//}
