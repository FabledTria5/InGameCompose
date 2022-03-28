plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc03"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Kotlin
    implementation(dependencyNotation = "androidx.core:core-ktx:1.7.0")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    // Design
    implementation(dependencyNotation = "androidx.appcompat:appcompat:1.4.1")
    implementation(dependencyNotation = "com.google.android.material:material:1.5.0")
    implementation(dependencyNotation = "com.google.android.exoplayer:exoplayer:2.17.1")

    // Compose
    val composeVersion = "1.1.1"
    implementation(dependencyNotation = "androidx.compose.ui:ui:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.material:material:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.ui:ui-tooling:$composeVersion")

    // Accompanist
    val accompanistVersion = "0.24.5-alpha"
    api(dependencyNotation = "com.google.accompanist:accompanist-pager:$accompanistVersion")
    api(dependencyNotation = "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion")
    api(dependencyNotation = "com.google.accompanist:accompanist-flowlayout:$accompanistVersion")

    // Testing
    testImplementation(dependencyNotation = "junit:junit:4.13.2")
    androidTestImplementation(dependencyNotation = "androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation(dependencyNotation = "androidx.test.ext:junit:1.1.3")

    // Coil
    implementation(dependencyNotation = "io.coil-kt:coil-compose:1.4.0")

    // Timber
    api(dependencyNotation = "com.jakewharton.timber:timber:5.0.1")
}