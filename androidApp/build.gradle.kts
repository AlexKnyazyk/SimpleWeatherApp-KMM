plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.simple.weather.app.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    composeOptions.kotlinCompilerExtensionVersion = "1.1.0"

    buildFeatures.compose = true
}

dependencies {
    implementation(project(":common"))

    implementation("io.insert-koin:koin-android:3.1.5")
    implementation("io.insert-koin:koin-androidx-compose:3.1.5")

    val composeVersion = "1.1.0"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.navigation:navigation-compose:2.5.0-alpha02")
    implementation("io.coil-kt:coil-compose:1.4.0")

    implementation("com.google.accompanist:accompanist-swiperefresh:0.19.0")
    implementation("com.google.accompanist:accompanist-insets-ui:0.24.1-alpha")

    implementation("androidx.appcompat:appcompat:1.4.1")
}
