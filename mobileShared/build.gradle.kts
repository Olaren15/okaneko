plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

val okaneVersion: String by project

val kotlinResultVersion: String by project
val kodeinDiVersion: String by project
val firebaseAuthVersion: String by project

val androidCompileSdk: String by project
val androidMinSdk: String by project
val androidTargetSdk: String by project

version = okaneVersion

kotlin {
    android()
    iosX64()
    iosArm64()
    // TODO: add this back when kotlin-result supports it
    //iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")

        framework {
            baseName = "mobileShared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("dev.gitlive:firebase-auth:$firebaseAuthVersion")
                implementation("com.michael-bull.kotlin-result:kotlin-result:$kotlinResultVersion")
                implementation("org.kodein.di:kodein-di:$kodeinDiVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val androidMain by getting

        @Suppress("UNUSED_VARIABLE")
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        // val iosSimulatorArm64Main by getting

        @Suppress("UNUSED_VARIABLE")
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting

        @Suppress("UNUSED_VARIABLE")
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = androidCompileSdk.toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = androidMinSdk.toInt()
        targetSdk = androidTargetSdk.toInt()
    }
}