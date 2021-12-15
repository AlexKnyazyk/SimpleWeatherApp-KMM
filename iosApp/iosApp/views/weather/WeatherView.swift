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
        }.listStyle(InsetGroupedListStyle())
            .pullToRefresh(isShowing: $viewModel.isRefreshing) {
                viewModel.getWeather()
        }
        .onChange(of: viewModel.isRefreshing) { _ in }
    }
}

//struct WeatherView_Previews: PreviewProvider {
//    static var previews: some View {
//        WeatherView()
//    }
//}
