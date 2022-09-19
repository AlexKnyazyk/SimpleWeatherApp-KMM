plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.simple.weather.app.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.simple.weather.app.android"
        minSdk = 21
        targetSdk = 33
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
        freeCompilerArgs = freeCompilerArgs + listOf(
//            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-Xopt-in=androidx.compose.ui.ExperimentalComposeUiApi"
        )
    }

    composeOptions.kotlinCompilerExtensionVersion = "1.1.0"

    buildFeatures.compose = true
}

dependencies {
    implementation(project(":common"))

    implementation("io.insert-koin:koin-androidx-compose:3.2.0")

    val composeVersion = "1.2.1"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("io.coil-kt:coil-compose:2.2.1")

    val accompanistVersion = "0.25.1"
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-insets-ui:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanistVersion")

    implementation("androidx.appcompat:appcompat:1.5.1")
}
