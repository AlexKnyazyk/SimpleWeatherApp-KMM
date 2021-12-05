//
//  MainTabView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct MainTabView: View {
    
    var body: some View {
        TabView {
            HomeWeatherView()
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("Home")
                }
            FavoriteLocationsView()
                .tabItem {
                    Image(systemName: "star.fill")
                    Text("Favorites")
                }
        }
    }
}

struct MainTabView_Previews: PreviewProvider {
    static var previews: some View {
        MainTabView()
    }
}
