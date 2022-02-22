plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.fabledt5.ingamecompose"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc03"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":data:repository"))
    implementation(project(":navigation"))
    implementation(project(":features:splash"))
    implementation(project(":features:authentication"))

    // Kotlin
    implementation(dependencyNotation = "androidx.core:core-ktx:1.7.0")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation(dependencyNotation = "com.google.firebase:firebase-auth-ktx:21.0.1")

    // Design
    implementation(dependencyNotation = "androidx.appcompat:appcompat:1.4.1")
    implementation(dependencyNotation = "com.google.android.material:material:1.5.0")

    // Compose
    val composeVersion = "1.1.0-rc03"
    implementation(dependencyNotation = "androidx.compose.ui:ui:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.material:material:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.ui:ui-tooling:$composeVersion")
    implementation(dependencyNotation = "androidx.activity:activity-compose:1.4.0")
    androidTestImplementation(dependencyNotation = "androidx.compose.ui:ui-test-junit4:$composeVersion")

    // Accompanist
    val accompanistVersion = "0.24.1-alpha"
    implementation(dependencyNotation = "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")

    // Compose Navigation
    val navigationVersion = "0.24.1-alpha"
    implementation(dependencyNotation = "com.google.accompanist:accompanist-navigation-animation:$navigationVersion")

    // Dagger Hilt
    implementation(dependencyNotation = "com.google.dagger:hilt-android:2.40.5")
    implementation(dependencyNotation = "androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt(dependencyNotation = "com.google.dagger:hilt-android-compiler:2.40.5")

    // Testing
    testImplementation(dependencyNotation = "junit:junit:4.13.2")
    androidTestImplementation(dependencyNotation = "androidx.test.ext:junit:1.1.3")
    androidTestImplementation(dependencyNotation = "androidx.test.espresso:espresso-core:3.4.0")

    // Timber
    api(dependencyNotation = "com.jakewharton.timber:timber:5.0.1")
}