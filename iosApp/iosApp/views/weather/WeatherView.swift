//
//  WeatherView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct WeatherView: View {
    
    @ObservedObject var viewModel: WeatherViewModel
    
    init(viewModel: WeatherViewModel) {
        self.viewModel = viewModel
        UITableView.appearance().sectionFooterHeight = 0
    }
    
    var body: some View {
        viewForState(uiState: viewModel.uiState)
        
    }
    
    @ViewBuilder
    func viewForState(uiState: UiState<WeatherModelUi>) -> some View {
        switch uiState {
        case .loading:
            VStack {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle())
            }
            
        case .data(let model):
            
            List {
                Section {
                    CurrentWeatherView(currentWeather: model.currentWeather, settingsUnits: model.settingsUnits)
                }
                Section {
                    WeatherForecasetView()
                        .listRowInsets(EdgeInsets())
                }
                Section {
                    WeatherDetailedView()
                }
            }.listStyle(InsetGroupedListStyle())
                .pullToRefresh(isShowing: $viewModel.isRefreshing) {
                    viewModel.getWeather(pullToRefresh: true)
                }
                .onChange(of: viewModel.isRefreshing) { _ in }
            
        case .error:
            EmptyView()
        }
    }
}
    
    //struct WeatherView_Previews: PreviewProvider {
    //    static var previews: some View {
    //        WeatherView()
    //    }
    //}
