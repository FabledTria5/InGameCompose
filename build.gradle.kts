// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(dependencyNotation = "com.android.tools.build:gradle:7.1.3")
        classpath(dependencyNotation = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath(dependencyNotation = "com.google.dagger:hilt-android-gradle-plugin:2.40.5")
        classpath(dependencyNotation = "com.google.gms:google-services:4.3.10")
    }
}

allprojects {
    repositories {
        google()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}