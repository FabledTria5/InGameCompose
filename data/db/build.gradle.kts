plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"
    id("dagger.hilt.android.plugin")
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

    // Kotlin
    implementation(dependencyNotation = "androidx.core:core-ktx:1.7.0")

    // Testing
    testImplementation(dependencyNotation = "junit:junit:4.13.2")
    androidTestImplementation(dependencyNotation = "androidx.test.ext:junit:1.1.3")

    // Room
    val roomVersion = "2.4.2"
    implementation(dependencyNotation = "androidx.room:room-runtime:$roomVersion")
    implementation(dependencyNotation = "androidx.room:room-ktx:$roomVersion")
    implementation(dependencyNotation = "androidx.room:room-paging:$roomVersion")
    ksp(dependencyNotation = "androidx.room:room-compiler:$roomVersion")

    // Dagger Hilt
    implementation(dependencyNotation = "com.google.dagger:hilt-android:2.40.5")
    kapt(dependencyNotation = "com.google.dagger:hilt-android-compiler:2.40.5")

    // Pagination
    val pagingVersion = "3.1.1"
    implementation(dependencyNotation = "androidx.paging:paging-runtime:$pagingVersion")

}