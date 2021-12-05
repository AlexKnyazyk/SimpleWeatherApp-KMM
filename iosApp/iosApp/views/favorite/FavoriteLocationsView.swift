//
//  FavoriteLocationsView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct FavoriteLocationsView: View {
    var body: some View {
        NavigationView {
            List {
                
                NavigationLink(destination: WeatherDetailsView()) {
                    Text("Favorite Locations")
                }
                
            }.navigationTitle(Text("Simple Weather App"))
        }
    }
    
    struct FavoriteLocationsView_Previews: PreviewProvider {
        static var previews: some View {
            FavoriteLocationsView()
        }
    }
}
