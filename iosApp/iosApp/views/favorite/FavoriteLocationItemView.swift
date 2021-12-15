//
//  FavoriteLOcationItemView.swift
//  iosApp
//
//  Created by a.knyazuk on 6.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct FavoriteLocationItemView: View {
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text("Minsk, region").font(.body)
                Text("Minsk").font(.caption)
                Text("Belarus").font(.caption2)
            }
            Spacer()
            Text("-2 C").font(.title)
                .padding(.leading, 8)
                .padding(.trailing, 8)
            ImageForUrl(
                url: URL(string: "https://cdn.weatherapi.com/weather/64x64/day/329.png")!,
                placeholder: { Text("-")}
            )
            .frame(width: 40, height: 40)
        }
    }
}

struct FavoriteLOcationItemView_Previews: PreviewProvider {
    static var previews: some View {
        FavoriteLocationItemView()
    }
}
