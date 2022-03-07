import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("android")

    id("com.android.application")
    id("com.google.gms.google-services") version ("4.3.10")
}

val composeVersion by extra("1.1.1")

val keystorePropertiesFile = project.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "dev.olaren.okane.android"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String?
            keyPassword = keystoreProperties["keyPassword"] as String?
            storeFile = keystoreProperties["storeFile"]?.let { file(it) }
            storePassword = keystoreProperties["storePassword"] as String?
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = project.extra["composeVersion"] as String
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))

    // compose
    implementation("androidx.navigation:navigation-compose:2.4.1")
    implementation("androidx.compose.ui:ui:${project.extra["composeVersion"]}")
    implementation("androidx.compose.material:material:${project.extra["composeVersion"]}")
    implementation("androidx.compose.material:material-icons-extended:${project.extra["composeVersion"]}")
    implementation("androidx.compose.material:material-icons-core:${project.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui-tooling-preview:${project.extra["composeVersion"]}")
    implementation("androidx.activity:activity-compose:1.4.0")

    // Dependency injection
    implementation("org.kodein.di:kodein-di-framework-compose:7.10.0")

    // Result
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.14")

    // tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${project.extra["composeVersion"]}")

    // compose debug
    debugImplementation("androidx.compose.ui:ui-tooling:${project.extra["composeVersion"]}")
}