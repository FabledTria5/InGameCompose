plugins {
    id("kotlin")
}

java {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

dependencies {
    implementation(dependencyNotation = Dependencies.coroutinesNative)
    implementation(dependencyNotation = Dependencies.javaInject)
    implementation(dependencyNotation = Dependencies.pagingCommon)

    testImplementation(dependencyNotation = Dependencies.junit)
}