//
//  WeatherViewModel.swift
//  iosApp
//
//  Created by a.knyazuk on 15.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import common
import SwiftUI

class WeatherViewModel : ObservableObject {
    
    private let getWeatherUseCase: IGetWeatherUseCase
    private let settingsRepository: SettingsRepository
    
    
    @State
    var isRefreshing: Bool = false
    @State
    var forecastMode: ForecastModeUi = .hourly
    
    init(
        getWeatherUseCase: IGetWeatherUseCase,
        settingsRepository: SettingsRepository
    ) {
        self.getWeatherUseCase = getWeatherUseCase
        self.settingsRepository = settingsRepository
    }
    
    func getWeather(pullToRefresh: Bool) {
        isRefreshing = true
        getWeatherUseCase.invoke(request: WeatherRequest.AutoIPAddress(), completionHandler: { [weak self] result, error in
            
            isRefreshing = false
        })
    }
}
