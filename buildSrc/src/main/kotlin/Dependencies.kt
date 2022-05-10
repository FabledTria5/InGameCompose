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
    const val composeCompilerExtensionVersion = "1.2.0-alpha08"

    val javaVersion = JavaVersion.VERSION_11
}

object Versions {

    // Core
    const val gradle = "7.1.3"
    const val kotlinGradleVersion = "1.6.20"
    const val googleServices = "4.3.10"
    const val kspVersion = "1.6.20-1.0.5"

    // Kotlin
    const val kotlinVersion = "1.7.0"
    const val lifecycleRuntime = "2.4.1"

    // Firebase
    const val firebaseAuth = "21.0.3"
    const val fireStore = "24.1.1"
    const val coroutinesNative = "1.6.0-native-mt"

    // Design
    const val appCompat = "1.4.1"
    const val material = "1.5.0"
    const val exoPlayer = "2.17.1"

    // Compose
    const val composeStable = "1.1.1"
    const val composeLatest = "1.2.0-alpha07"
    const val activityCompose = "1.4.0"
    const val accompanist = "0.23.1"
    const val coil = "1.4.0"
    const val hiltCompose = "1.0.0"

    // Dagger Hilt
    const val hiltAndroid = "2.40.5"

    // Testing
    const val jUnit = "4.13.2"
    const val androidJUnit = "1.1.3"
    const val espressoCore = "3.4.0"

    // Timber
    const val timber = "5.0.1"

    // Room
    const val roomVersion = "2.4.2"

    // Preferences
    const val datastorePreferences = "1.0.0"

    // Retrofit
    const val retrofitVersion = "2.9.0"
    const val loggingInterceptor = "5.0.0-alpha.3"

    // Jsoup
    const val jsoup = "1.14.3"

    // Pagination
    const val pagingVersion = "3.1.1"
}

object Dependencies {

    // Core
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradleVersion}"
    const val hiltAndroidPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroid}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"

    // Kotlin
    const val kotlinCoreKtx = "androidx.core:core-ktx:${Versions.kotlinVersion}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleRuntime}"
    const val javaInject = "javax.inject:javax.inject:1"
    const val coroutinesNative =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesNative}"

    // Firebase
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx:${Versions.firebaseAuth}"
    const val fireStore = "com.google.firebase:firebase-firestore-ktx:${Versions.fireStore}"
    const val firebaseCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesNative}"

    // Design
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val exoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.exoPlayer}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    // Compose Latest
    const val composeUiLatest = "androidx.compose.ui:ui:${Versions.composeLatest}"
    const val composeMaterialLatest = "androidx.compose.material:material:${Versions.composeLatest}"
    const val composeToolingPreviewLatest =
        "androidx.compose.ui:ui-tooling-preview:${Versions.composeLatest}"
    const val composeToolingLatest = "androidx.compose.ui:ui-tooling:${Versions.composeLatest}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val junitComposeLatest = "androidx.compose.ui:ui-test-junit4:${Versions.composeLatest}"
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.composeLatest}"

    // Compose Stable
    const val composeUiStable = "androidx.compose.ui:ui:${Versions.composeStable}"
    const val composeMaterialStable = "androidx.compose.material:material:${Versions.composeStable}"
    const val composeToolingPreviewStable =
        "androidx.compose.ui:ui-tooling-preview:${Versions.composeStable}"
    const val composeToolingStable = "androidx.compose.ui:ui-tooling:${Versions.composeStable}"
    const val composeViewBindingStable =
        "androidx.compose.ui:ui-viewbinding:${Versions.composeStable}"

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
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
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
}