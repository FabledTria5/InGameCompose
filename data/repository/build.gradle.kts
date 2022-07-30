plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
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
            isMinifyEnabled = true
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
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data:preferences"))
    implementation(project(":data:db"))
    implementation(project(":data:remote"))

    // Kotlin
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)
    implementation(dependencyNotation = Dependencies.firebaseAuth)
    implementation(dependencyNotation = Dependencies.fireStore)
    implementation(dependencyNotation = Dependencies.firebaseDatabase)
    implementation(dependencyNotation = Dependencies.firebaseCoroutines)
    coreLibraryDesugaring(dependencyNotation = Dependencies.desugar)

    // Testing
    testImplementation(dependencyNotation = Dependencies.junit)
    androidTestImplementation(dependencyNotation = Dependencies.espressoCore)

    // Dagger Hilt
    implementation(dependencyNotation = Dependencies.hiltAndroid)
    kapt(dependencyNotation = Dependencies.hiltCompiler)

    // Pagination
    implementation(dependencyNotation = Dependencies.pagingRuntime)

    // Timber
    implementation(dependencyNotation = Dependencies.timber)

}