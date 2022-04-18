buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    val kotlinVersion: String by project
    val androidGradlePluginVersion: String by project
    val shadowPluginVersion: String by project

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.android.tools.build:gradle:$androidGradlePluginVersion")
        classpath("gradle.plugin.com.github.johnrengelman:shadow:$shadowPluginVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

/*
 * Heroku buildpacks execute the stage task by default
 * Here we compile the server component to a fat jar.
 */
tasks.register("stage") {
    dependsOn(":server:shadowJar")
}