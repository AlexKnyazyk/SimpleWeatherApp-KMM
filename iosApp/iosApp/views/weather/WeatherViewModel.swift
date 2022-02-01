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
    @State
    var uiState: UiState<WeatherModelUi> = .loading
    
    init(
        getWeatherUseCase: IGetWeatherUseCase,
        settingsRepository: SettingsRepository
    ) {
        self.getWeatherUseCase = getWeatherUseCase
        self.settingsRepository = settingsRepository
        getWeather(pullToRefresh: false)
    }
    
    func getWeather(pullToRefresh: Bool) {
        if (pullToRefresh) {
            self.isRefreshing = true
        } else {
            self.uiState = .loading
        }
        
        print("getWeather: \(pullToRefresh)")
        getWeatherUseCase.invoke(request: WeatherRequest.AutoIPAddress(), completionHandler: { [weak self] result, error in
            
            let model: WeatherModel? = result?.getOrNull()
            print("result: \(model)")
            print("self: \(self)")
            if model != nil {
                self?.uiState = UiState.data(data: WeatherModelUi(weatherModel: model!, settingsUnitsModel: SettingsUnitsModel(isTempMetric: true, isDistanceMetric: true)))
            } else {
                self?.uiState = UiState.error
            }
            
            print("uiState: \(self?.uiState)")
            self?.isRefreshing = false
        })
    }
}
