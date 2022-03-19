pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Okaneko"
include(":android")
include(":mobileShared")
include(":shared")
include(":server")
