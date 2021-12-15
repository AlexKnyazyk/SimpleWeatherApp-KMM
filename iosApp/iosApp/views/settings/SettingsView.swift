//
//  SettingsView.swift
//  iosApp
//
//  Created by a.knyazuk on 5.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SettingsView: View {
    
    private let temperatureUnits = ["C", "F"]
    private let speedUnits = ["kilometers", "miles"]
    
    @State private var temperatureUnitSelection: Int = 0
    @State private var speedUnitSelection: Int = 0
    
    var body: some View {
        Form {
            Section(header: Text("Units")) {
                Picker(selection: $temperatureUnitSelection, label: Text("Temperature")) {
                    ForEach(0 ..< temperatureUnits.count) {
                        Text(self.temperatureUnits[$0])
                    }
                }
                Picker(selection: $speedUnitSelection, label: Text("Speed/Distance")) {
                    ForEach(0 ..< speedUnits.count) {
                        Text(self.speedUnits[$0])
                    }
                }
            }
        }.navigationTitle("Settings")
            .navigationBarTitleDisplayMode(.inline)
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
    }
}
