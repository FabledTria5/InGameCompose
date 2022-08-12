// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply {
    plugin(Plugins.gradleVersions)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(dependencyNotation = Dependencies.gradle)
        classpath(dependencyNotation = Dependencies.kotlinGradlePlugin)
        classpath(dependencyNotation = Dependencies.hiltAndroidPlugin)
        classpath(dependencyNotation = Dependencies.googleServices)
        classpath(dependencyNotation = Dependencies.gradleDependencies)
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