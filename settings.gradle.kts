enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ArsirStore"
include(":app")
include(":uikit")
include(":feature:splash-screen")
include(":data:splash-screen")
include(":core:coroutines")
include(":core:network")
include(":core:datastore")
include(":domain:splash-screen")
include(":observer")
include(":navigation")
include(":data:login")
include(":domain:login")
include(":core:common")
include(":core:remote")
include(":feature:login")
include(":feature:home")
include(":data:product")
include(":domain:product")
include(":core:database")
include(":feature:detail")
include(":feature:onboarding")
include(":domain:onboarding")
include(":data:onboarding")
