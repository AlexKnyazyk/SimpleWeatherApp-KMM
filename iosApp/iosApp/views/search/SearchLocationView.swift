//
//  SearchLocationView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SearchLocationView: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @State private var searchText: String = ""
    
    var body: some View {
        VStack(alignment: .leading) {
            SearchBar(text: $searchText)
            List {
                Button(action: {
                    self.presentationMode.wrappedValue.dismiss()
                }) {
                    SearchLocationItemView()
                }
                Button(action: {
                    self.presentationMode.wrappedValue.dismiss()
                }) {
                    SearchLocationItemView()
                }
            }.listStyle(PlainListStyle())
        }.navigationBarTitleDisplayMode(.inline)
    }
}

struct SearchLocationView_Previews: PreviewProvider {
    static var previews: some View {
        SearchLocationView()
    }
}
