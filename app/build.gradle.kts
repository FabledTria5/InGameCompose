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
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isRenderscriptDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

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
    implementation(project(":features:collections"))

    // Kotlin
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)
    implementation(dependencyNotation = Dependencies.lifecycleRuntime)
    implementation(dependencyNotation = Dependencies.firebaseAuth)
    coreLibraryDesugaring(dependencyNotation = Dependencies.desugar)

    // Design
    implementation(dependencyNotation = Dependencies.appcompat)
    implementation(dependencyNotation = Dependencies.material)

    // Accompanist
    implementation(dependencyNotation = Dependencies.systemUiController)

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