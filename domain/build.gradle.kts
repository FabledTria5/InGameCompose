plugins {
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
    implementation(dependencyNotation = "javax.inject:javax.inject:1")
    implementation(dependencyNotation = "androidx.paging:paging-common:3.1.1")

    testImplementation(dependencyNotation = "junit:junit:4.13.2")
}