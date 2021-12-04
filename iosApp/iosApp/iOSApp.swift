import SwiftUI
import common

@main
struct iOSApp: App {
    
    private let di = DiContainer()
    
    init() {
        di.doInit()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
