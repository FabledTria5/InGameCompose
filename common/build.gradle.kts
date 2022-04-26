plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = Config.testRunner
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.composeCompilerExtensionVersion
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTargetVersion
    }
}

dependencies {

    // Kotlin
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)
    implementation(dependencyNotation = Dependencies.lifecycleRuntime)

    // Design
    implementation(dependencyNotation = Dependencies.appcompat)
    implementation(dependencyNotation = Dependencies.material)
    implementation(dependencyNotation = Dependencies.exoPlayer)

    // Compose
    implementation(dependencyNotation = Dependencies.composeUiStable)
    implementation(dependencyNotation = Dependencies.composeMaterialStable)
    implementation(dependencyNotation = Dependencies.composeToolingPreviewStable)
    implementation(dependencyNotation = Dependencies.composeToolingStable)
    implementation(dependencyNotation = Dependencies.composeViewBindingStable)

    // Accompanist
    api(dependencyNotation = Dependencies.pager)
    api(dependencyNotation = Dependencies.pagerIndicators)
    api(dependencyNotation = Dependencies.flowLayout)

    // Testing
    testImplementation(dependencyNotation = Dependencies.junit)
    androidTestImplementation(dependencyNotation = Dependencies.androidJunit)
    androidTestImplementation(dependencyNotation = Dependencies.espressoCore)

    // Coil
    implementation(dependencyNotation = Dependencies.coil)

    // Timber
    api(dependencyNotation = Dependencies.timber)
}