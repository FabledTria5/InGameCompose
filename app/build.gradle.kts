plugins {
    id(Plugins.application)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.googleServices)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testRunner
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
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTargetVersion
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.composeCompilerExtensionVersion
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
    implementation(project(":features:home"))
    implementation(project(":features:game"))
    implementation(project(":features:catalogue"))

    // Kotlin
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)
    implementation(dependencyNotation = Dependencies.lifecycleRuntime)
    implementation(dependencyNotation = Dependencies.firebaseAuth)

    // Design
    implementation(dependencyNotation = Dependencies.appcompat)
    implementation(dependencyNotation = Dependencies.material)

    // Compose
    implementation(dependencyNotation = Dependencies.composeUi)
    implementation(dependencyNotation = Dependencies.composeMaterial)
    implementation(dependencyNotation = Dependencies.composeToolingPreview)
    implementation(dependencyNotation = Dependencies.composeTooling)
    implementation(dependencyNotation = Dependencies.activityCompose)
    androidTestImplementation(dependencyNotation = Dependencies.junitCompose)

    // Accompanist
    implementation(dependencyNotation = Dependencies.systemUiController)
    implementation(dependencyNotation = Dependencies.pager)

    // Compose Navigation
    implementation(dependencyNotation = Dependencies.composeNavigation)

    // Dagger Hilt
    implementation(dependencyNotation = Dependencies.hiltAndroid)
    implementation(dependencyNotation = Dependencies.hiltCompose)
    kapt(dependencyNotation = Dependencies.hiltCompiler)

    // Testing
    testImplementation(dependencyNotation = Dependencies.junit)
    androidTestImplementation(dependencyNotation = Dependencies.androidJunit)
    androidTestImplementation(dependencyNotation = Dependencies.espressoCore)
}