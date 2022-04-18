plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

val okaneVersion: String by project
val kotlinDateTimeVersion: String by project
val kotlinResultVersion: String by project
val kodeinDiVersion: String by project
val firebaseAuthVersion: String by project

val androidCompileSdk: String by project
val androidMinSdk: String by project
val androidTargetSdk: String by project

version = okaneVersion

kotlin {
    jvm()
    android()

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDateTimeVersion")
                implementation("com.michael-bull.kotlin-result:kotlin-result:$kotlinResultVersion")
                implementation("org.kodein.di:kodein-di:$kodeinDiVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting
        val jvmTest by getting

        val androidMain by getting
        val androidAndroidTestRelease by getting
        val androidTest by getting {
            dependsOn(androidAndroidTestRelease)
        }
    }
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = androidCompileSdk.toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = androidMinSdk.toInt()
        targetSdk = androidTargetSdk.toInt()
    }
}