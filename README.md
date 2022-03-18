# SimpleWeatherApp
This project is meant as a training project for running in the latest technologies such as KMM, Jetpack Compose and SwiftUI.
It uses a public free api to get up to date weather information:
https://www.weatherapi.com/docs/
### Technologies
###### Android
- Jetpack Compose
- Material Theme
- Google Accompanist helper libs
  - SwipereTofresh
  - Insets
  - SystemUIcontroller (keyboard controller)
  - Navigation animations
- Coil for compose
- Koin

###### iOS
- SwiftUi
- TODO

###### Common (KMM)
- Coroutines
- Ktor
- Kotlin json serialization
- SqlDelight
- Koin

### Architecture
**Clean architecure.**
- **Data:** DataSource local/remote, RepositoryImpl
- **Domain:** Models, UseCase, Interacotor, IRepository
- **Presentation:** UI, MVVM, Jetpack Compose, SwiftUI

Data and domain layers are placed in a common KMM module

### Implementation Steps
- [x] Android native app
- [x] Extract Data and Domain layers to KMM **common** module
- [x] Prepare basic **SwiftUi** screens for iOS without logic
- [x] Migrate Android App to **Jetpack Compose**
- [ ] Connect KMM module logic to iOS **SwiftUi**. TODO

## Results
#### Android
<img src="https://i.ibb.co/2gd1hjZ/android-1.jpg" alt="android-1" width="200"> <img src="https://i.ibb.co/XzmM3jx/android-2.jpg" alt="android-2" width="200"> <img src="https://i.ibb.co/m6YxBNT/android-3.jpg" alt="android-3" width="200"> <img src="https://i.ibb.co/z5rKbB5/android-4.jpg" alt="android-4" width="200">

#### iOS
<img src="https://i.ibb.co/vP5w3YB/ios-1.jpg" alt="ios-1" width="200"> <img src="https://i.ibb.co/fF2dFfH/ios-2.jpg" alt="ios-2" width="200"> <img src="https://i.ibb.co/yf1k4yb/ios-3.jpg" alt="ios-3" width="200"> <img src="https://i.ibb.co/P9B0dX6/ios-4.jpg" alt="ios-4" width="200">