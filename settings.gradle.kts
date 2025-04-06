rootProject.name = "AboutMe"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
//        maven( "https://androidx.dev/storage/compose-compiler/repository")
//        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")

    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
//        maven( "https://androidx.dev/storage/compose-compiler/repository")
//        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}


plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}
include(":composeApp")
include(":shared")
include(":feature:home")
include(":base:theme")
include(":base:composables")
include(":base:utils")
