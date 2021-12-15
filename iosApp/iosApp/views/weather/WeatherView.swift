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
        }.listStyle(InsetGroupedListStyle())
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

struct WeatherView_Previews: PreviewProvider {
    static var previews: some View {
        WeatherView()
    }
}
