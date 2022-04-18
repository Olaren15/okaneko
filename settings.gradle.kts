pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Okaneko"
include(":android")
include(":clientShared")
include(":shared")
include(":server")
