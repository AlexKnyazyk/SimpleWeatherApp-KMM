//
//  MainTabView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct MainTabView: View {
    
    @State private var rootNavigation: String? = nil
    
    var body: some View {
        NavigationView {
            VStack {
                TabView {
                    HomeWeatherView(rootNavigation: $rootNavigation)
                        .tabItem {
                            Image(systemName: "house.fill")
                            Text("title_home")
                        }
                    FavoriteLocationsView(rootNavigation: $rootNavigation)
                        .tabItem {
                            Image(systemName: "star.fill")
                            Text("title_favorites")
                        }
                }.navigationBarHidden(true)
                
                NavigationLink(destination: SettingsView(), tag: "Settings", selection: $rootNavigation) {
                    EmptyView()
                }
                NavigationLink(destination: SearchLocationView(), tag: "SearchLocation", selection: $rootNavigation) {
                    EmptyView()
                }
            }.navigationBarHidden(true)
        }
    }
}

struct MainTabView_Previews: PreviewProvider {
    static var previews: some View {
        MainTabView()
    }
}
