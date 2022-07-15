plugins {
    id(Plugins.kotlin)
}

java {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

dependencies {
    implementation(dependencyNotation = Dependencies.coroutinesCore)
    implementation(dependencyNotation = Dependencies.javaInject)
    implementation(dependencyNotation = Dependencies.pagingCommon)

    testImplementation(dependencyNotation = Dependencies.junit)
}