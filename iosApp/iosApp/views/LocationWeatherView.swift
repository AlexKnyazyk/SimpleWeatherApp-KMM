//
//  WeatherDetailsView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct LocationWeatherView: View {

    private let viewModel: WeatherViewModel = WeatherViewModel(
        getWeatherUseCase: iOSApp.di.getWeatherUseCase(),
        settingsRepository: iOSApp.di.getSettingsRepository()
    )
    
    var body: some View {
        WeatherView(viewModel: viewModel)
            .navigationTitle("Minsk")
            .navigationBarTitleDisplayMode(.inline)
    }
}

struct WeatherDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        LocationWeatherView()
    }
}
