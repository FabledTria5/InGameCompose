plugins {
    id(Plugins.library)
    kotlin(Plugins.android)
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

    implementation(project(":domain"))

    // Kotlin
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)
    implementation(dependencyNotation = Dependencies.lifecycleRuntime)

    // Design
    implementation(dependencyNotation = Dependencies.appcompat)
    implementation(dependencyNotation = Dependencies.material)
    implementation(dependencyNotation = Dependencies.exoPlayer)

    // Compose
    implementation(dependencyNotation = Dependencies.composeViewBinding)
    api(dependencyNotation = Dependencies.composeUi)
    api(dependencyNotation = Dependencies.composeMaterial)
    api(dependencyNotation = Dependencies.activityCompose)

    // Compose Preview
    api(dependencyNotation = Dependencies.composeToolingPreview)
    debugApi(dependencyNotation = Dependencies.composeTooling)
    debugApi(dependencyNotation = Dependencies.customview)
    debugApi(dependencyNotation = Dependencies.poolingContainer)

    // Accompanist
    implementation(dependencyNotation = Dependencies.pager)

    // Testing
    testApi(dependencyNotation = Dependencies.junit)
    androidTestApi(dependencyNotation = Dependencies.androidJunit)
    androidTestApi(dependencyNotation = Dependencies.espressoCore)
    androidTestApi(dependencyNotation = Dependencies.junitCompose)

    // Coil
    implementation(dependencyNotation = Dependencies.coil)

    // Timber
    api(dependencyNotation = Dependencies.timber)
}