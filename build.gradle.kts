// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(dependencyNotation = Dependencies.gradle)
        classpath(dependencyNotation = Dependencies.kotlinGradlePlugin)
        classpath(dependencyNotation = Dependencies.hiltAndroidPlugin)
        classpath(dependencyNotation = Dependencies.googleServices)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
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