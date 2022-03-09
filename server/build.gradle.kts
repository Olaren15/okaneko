plugins {
    application
    kotlin("jvm")
}

val okaneVersion: String by project

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

group = "dev.olaren.okane"
version = okaneVersion
application {
    mainClass.set("dev.olaren.okane.ApplicationKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}