plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    kotlin(Plugins.serialization) version Versions.kotlinGradleVersion
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
            buildConfigField("String", "APIKEY", "\"f02ee92cf89a46bea018996a1b45fd3f\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "APIKEY", "\"f02ee92cf89a46bea018996a1b45fd3f\"")
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
}

dependencies {

    // Kotlin
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)
    implementation(dependencyNotation = Dependencies.kotlinSerialization)

    // Testing
    testImplementation(dependencyNotation = Dependencies.junit)
    androidTestImplementation(dependencyNotation = Dependencies.androidJunit)

    // Retrofit
    implementation(dependencyNotation = Dependencies.retrofit)
    implementation(dependencyNotation = Dependencies.gsonConverter)
    implementation(dependencyNotation = Dependencies.kotlinSerializationConverter)
    implementation(dependencyNotation = Dependencies.loggingInterceptor)

    // Jsoup
    implementation(dependencyNotation = Dependencies.jsoup)

    // Dagger Hilt
    implementation(dependencyNotation = Dependencies.hiltAndroid)
    kapt(dependencyNotation = Dependencies.hiltCompiler)
}