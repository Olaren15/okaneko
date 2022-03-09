import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("android")

    id("com.android.application")
    id("com.google.gms.google-services")
}

val okaneVersion: String by project

val kodeinDiVersion: String by project
val kotlinResultVersion: String by project

val jetpackComposeVersion: String by project
val navigationComposeVersion: String by project
val activityComposeVersion: String by project

val androidCompileSdk: String by project
val androidMinSdk: String by project
val androidTargetSdk: String by project

val keystorePropertiesFile = project.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    compileSdk = androidCompileSdk.toInt()
    defaultConfig {
        applicationId = "dev.olaren.okane.android"
        minSdk = androidMinSdk.toInt()
        targetSdk = androidTargetSdk.toInt()
        versionCode = 1
        versionName = okaneVersion
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
        kotlinCompilerExtensionVersion = jetpackComposeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":mobileShared"))

    // compose
    implementation("androidx.navigation:navigation-compose:$navigationComposeVersion")
    implementation("androidx.compose.ui:ui:$jetpackComposeVersion")
    implementation("androidx.compose.material:material:$jetpackComposeVersion")
    implementation("androidx.compose.material:material-icons-extended:$jetpackComposeVersion")
    implementation("androidx.compose.material:material-icons-core:$jetpackComposeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$jetpackComposeVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")

    // Dependency injection
    implementation("org.kodein.di:kodein-di-framework-compose:$kodeinDiVersion")

    // Result
    implementation("com.michael-bull.kotlin-result:kotlin-result:$kotlinResultVersion")

    // tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$jetpackComposeVersion")

    // compose debug
    debugImplementation("androidx.compose.ui:ui-tooling:$jetpackComposeVersion")
}