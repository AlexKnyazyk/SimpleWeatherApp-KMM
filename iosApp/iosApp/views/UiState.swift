//
//  UiState.swift
//  iosApp
//
//  Created by Alexey Knyazuk on 24.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

enum UiState<T> {
    case loading
    case data(data: T)
    case error
}
