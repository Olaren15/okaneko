plugins {
    kotlin("android")
    id("com.android.application")
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

@Suppress("UnstableApiUsage")
android {
    compileSdk = androidCompileSdk.toInt()
    defaultConfig {
        applicationId = "app.okaneko.android"
        minSdk = androidMinSdk.toInt()
        targetSdk = androidTargetSdk.toInt()
        versionCode = 1
        versionName = okaneVersion
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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
    implementation(project(":clientShared"))

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