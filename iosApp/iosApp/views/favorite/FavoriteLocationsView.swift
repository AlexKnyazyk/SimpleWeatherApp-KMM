//
//  FavoriteLocationsView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct FavoriteLocationsView: View {
    
    @Binding public var rootNavigation: String?
    
    var body: some View {
        NavigationView {
            List {
                NavigationLink(destination: LocationWeatherView()) {
                    FavoriteLocationItemView()
                }
                NavigationLink(destination: LocationWeatherView()) {
                    FavoriteLocationItemView()
                }
                NavigationLink(destination: LocationWeatherView()) {
                    FavoriteLocationItemView()
                }
                
            }.navigationTitle(Text("Favorites"))
                .navigationBarItems(
                    trailing: Button("Add") {
                        rootNavigation = "SearchLocation"
                    }
                )
        }
    }
}


//    struct FavoriteLocationsView_Previews: PreviewProvider {
//        static var previews: some View {
//            FavoriteLocationsView()
//        }
//    }
