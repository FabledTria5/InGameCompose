plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data:preferences"))
    implementation(project(":data:db"))
    implementation(project(":data:remote"))

    // Kotlin
    implementation(dependencyNotation = "androidx.core:core-ktx:1.7.0")
    implementation(dependencyNotation = "com.google.firebase:firebase-auth-ktx:21.0.1")
    implementation(dependencyNotation = "com.google.firebase:firebase-firestore-ktx:24.0.1")
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.0-native-mt")

    // Testing
    testImplementation(dependencyNotation = "junit:junit:4.13.2")
    androidTestImplementation(dependencyNotation = "androidx.test.ext:junit:1.1.3")

    // Dagger Hilt
    implementation(dependencyNotation = "com.google.dagger:hilt-android:2.40.5")
    kapt(dependencyNotation = "com.google.dagger:hilt-android-compiler:2.40.5")

    // Pagination
    val pagingVersion = "3.1.0"
    implementation(dependencyNotation = "androidx.paging:paging-runtime:$pagingVersion")

}