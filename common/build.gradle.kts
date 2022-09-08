plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.6.10"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "common"
        }
    }

    sourceSets {
        val ktorVersion = "1.6.7"
        val coroutinesVersion = "1.6.0"
        val sqldelightVersion = "1.5.3"
        val koinVersion = "3.2.0"
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                implementation("com.squareup.sqldelight:runtime:$sqldelightVersion")
                implementation("com.squareup.sqldelight:coroutines-extensions:$sqldelightVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("com.squareup.sqldelight:android-driver:$sqldelightVersion")
                implementation("io.insert-koin:koin-android:$koinVersion")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}-native-mt") {
                    version {
                        strictly("${coroutinesVersion}-native-mt")
                    }
                }
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:$sqldelightVersion")
            }
        }
    }
}

android {
    namespace = "com.simple.weather.app"
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    compileOptions {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.2.2")
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.simple.weather.app.data.db"
    }
}