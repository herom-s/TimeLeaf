pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TimeLeaf"

include(":app")
include(":feature")
include(":core:model")
include(":core:database")
include(":core:data")
include(":core:designsystem")
include(":feature:onboarding")
include(":core:network")
