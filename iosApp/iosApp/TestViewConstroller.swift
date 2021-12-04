//
//  TestViewConstroller.swift
//  iosApp
//
//  Created by a.knyazuk on 4.12.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit
import CoreLocation

class TestViewController : UIViewController {
    
    override func viewDidAppear(_ animated: Bool) {
        let locationManager = CLLocationManager()
        
        kCLLocationAccuracyReduced
        
        locationManager.accuracyAuthorization = .reducedAccuracy
        
        val status = locationManager.authorizationStatus
        
        locationManager.requestTemporaryFullAccuracyAuthorization(withPurposeKey: <#T##String#>, completion: <#T##((Error?) -> Void)?##((Error?) -> Void)?##(Error?) -> Void#>)()
        switch status {
        case .denied
        }
        status
    }
}
