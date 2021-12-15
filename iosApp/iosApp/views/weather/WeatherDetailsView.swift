//
//  WeatherDetailsView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct WeatherDetailsView: View {

    var body: some View {
        WeatherView()
            .navigationTitle("Minsk")
            .navigationBarTitleDisplayMode(.inline)
    }
}

struct WeatherDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        WeatherDetailsView()
    }
}
