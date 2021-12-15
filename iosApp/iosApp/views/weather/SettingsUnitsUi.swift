//
//  SettingsUnitsUi.swift
//  iosApp
//
//  Created by a.knyazuk on 15.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

struct SettingsUnitsUi {
    let isTempMetric: Bool
    let isDistanceMetric: Bool

    init() {
        isTempMetric = true
        isDistanceMetric = false
    }
    
    init(isTempMetric: Bool, isDistanceMetric: Bool) {
        self.isTempMetric = isTempMetric
        self.isDistanceMetric = isDistanceMetric
    }
}
