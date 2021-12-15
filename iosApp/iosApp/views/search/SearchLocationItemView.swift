//
//  SearchLocationItemView.swift
//  iosApp
//
//  Created by a.knyazuk on 6.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SearchLocationItemView: View {
    var body: some View {
        VStack(alignment: .leading) {
            Text("Minsk. City").font(.body)
            Text("Minsk region").font(.caption)
            Text("Belarus").font(.caption2)
        }
    }
}

struct SearchLocationItemView_Previews: PreviewProvider {
    static var previews: some View {
        SearchLocationItemView()
    }
}
