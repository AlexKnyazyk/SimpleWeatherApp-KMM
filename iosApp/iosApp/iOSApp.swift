import SwiftUI
import common

@main
struct iOSApp: App {
    
    static let di = DiContainer()
    
    init() {
        iOSApp.di.doInit()
    }
    
    var body: some Scene {
        WindowGroup {
            MainContentView()
                .environmentObject(
                    WeatherViewModel(
                        getWeatherUseCase: iOSApp.di.getWeatherUseCase(),
                        settingsRepository: iOSApp.di.getSettingsRepository()
                    )
                )
        }
    }
}
