dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "InGameCompose"
include(":app")
include(":common")
include(":features")
include(":data")
include(":domain")
include(":data:db")
include(":data:remote")
include(":data:preferences")
include(":data:repository")
include(":navigation")
include(":features:splash")
include(":features:authentication")
include(":features:home")
include(":features:game")
include(":features:catalogue")
include(":features:collections")
