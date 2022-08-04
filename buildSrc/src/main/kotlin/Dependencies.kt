import org.gradle.api.JavaVersion

object Config {
    const val compileSdk = 32
    const val minSdk = 23
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"

    const val applicationId = "com.fabledt5.ingamecompose"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val jvmTargetVersion = "11"
    const val composeCompilerExtensionVersion = "1.3.0-rc01"

    val javaVersion = JavaVersion.VERSION_11
}

object Versions {

    // Core
    const val gradle = "7.2.2"
    const val kotlinGradleVersion = "1.7.10"
    const val googleServices = "4.3.13"
    const val kspVersion = "1.7.10-1.0.6"
    const val gradleDependencies = "0.42.0"

    // Kotlin
    const val kotlinVersion = "1.8.0"
    const val lifecycleRuntime = "2.6.0-alpha01"
    const val kotlinSerialization = "1.4.0-RC"
    const val desugar = "1.1.5"

    // Firebase
    const val firebaseAuth = "21.0.6"
    const val fireStore = "24.2.1"
    const val firebaseDatabase = "20.0.5"
    const val coroutinesCore = "1.6.4"

    // Design
    const val appCompat = "1.6.0-alpha05"
    const val material = "1.6.0"

    // Compose
    const val compose = "1.3.0-alpha02"
    const val composeMaterial = "1.0.0-alpha15"
    const val activityCompose = "1.5.0"
    const val accompanist = "0.26.0-alpha"
    const val coil = "2.1.0"
    const val hiltCompose = "1.0.0"
    const val customview = "1.2.0-alpha01"
    const val poolingContainer = "1.0.0"
    const val constraintLayout = "1.1.0-alpha03"
    const val calendar = "0.6.0"
    const val extendedIcons = "1.3.0-alpha02"

    // Dagger Hilt
    const val hiltAndroid = "2.43.1"

    // Testing
    const val jUnit = "4.13.2"
    const val androidJUnit = "1.1.4-alpha07"
    const val espressoCore = "3.5.0-alpha07"

    // Timber
    const val timber = "5.0.1"

    // Room
    const val roomVersion = "2.5.0-alpha02"

    // Preferences
    const val datastorePreferences = "1.0.0"

    // Network
    const val retrofitVersion = "2.9.0"
    const val kotlinSerializationConverter = "0.8.0"
    const val loggingInterceptor = "5.0.0-alpha.10"

    // Jsoup
    const val jsoup = "1.15.2"

    // Pagination
    const val pagingVersion = "3.2.0-alpha01"
    const val pagingCompose = "1.0.0-alpha15"
}

object Dependencies {

    // Core
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradleVersion}"
    const val hiltAndroidPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroid}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val gradleDependencies =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleDependencies}"

    // Kotlin
    const val kotlinCoreKtx = "androidx.core:core-ktx:${Versions.kotlinVersion}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleRuntime}"
    const val javaInject = "javax.inject:javax.inject:1"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val kotlinSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
    const val desugar = "com.android.tools:desugar_jdk_libs:${Versions.desugar}"

    // Firebase
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx:${Versions.firebaseAuth}"
    const val fireStore = "com.google.firebase:firebase-firestore-ktx:${Versions.fireStore}"
    const val firebaseDatabase =
        "com.google.firebase:firebase-database-ktx:${Versions.firebaseDatabase}"
    const val firebaseCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesCore}"

    // Design
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    // Compose
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial}"
    const val composeToolingPreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val junitCompose = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val customview = "androidx.customview:customview:${Versions.customview}"
    const val poolingContainer =
        "androidx.customview:customview-poolingcontainer:${Versions.poolingContainer}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"
    const val calendar =
        "io.github.boguszpawlowski.composecalendar:composecalendar:${Versions.calendar}"
    const val extendedIcons =
        "androidx.compose.material:material-icons-extended:${Versions.extendedIcons}"

    // Accompanist
    const val systemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    const val composeNavigation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val pager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    const val pagerIndicators =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    const val flowLayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"

    // Dagger Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}"

    // Paging
    const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
    const val pagingCommon = "androidx.paging:paging-common:${Versions.pagingVersion}"
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.pagingCompose}"

    // Preferences
    const val dataStorePreferences =
        "androidx.datastore:datastore-preferences:${Versions.datastorePreferences}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomPaging = "androidx.room:room-paging:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val kotlinSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinSerializationConverter}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"

    // Testing
    const val junit = "junit:junit:${Versions.jUnit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    // Logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Plugins {
    const val application = "com.android.application"
    const val library = "com.android.library"
    const val android = "android"
    const val kotlin = "kotlin"
    const val kapt = "kapt"
    const val ksp = "com.google.devtools.ksp"
    const val hilt = "dagger.hilt.android.plugin"
    const val googleServices = "com.google.gms.google-services"
    const val serialization = "plugin.serialization"
    const val gradleVersions = "com.github.ben-manes.versions"
}