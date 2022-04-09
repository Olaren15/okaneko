plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
}

val okaneVersion: String by project
val kodeinDiServerVersion: String by project
val kotlinResultVersion: String by project
val kotlinDateTimeVersion: String by project

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val kmongoVersion: String by project
val bcryptVersion: String by project
val kotlinUuidVersion: String by project

group = "app.okaneko"
version = okaneVersion

tasks {
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "io.ktor.server.netty.EngineMain"))
        }
    }
}

repositories {
    // For Kodein-di 8.0 snapshot
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation(project(":shared"))

    // Ktor
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // Result
    implementation("com.michael-bull.kotlin-result:kotlin-result:$kotlinResultVersion")

    // MongoDB
    implementation("org.litote.kmongo:kmongo-coroutine-serialization:$kmongoVersion")

    // Kotlinx
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDateTimeVersion")

    // Bcrypt
    implementation("at.favre.lib:bcrypt:$bcryptVersion")

    // Kodein
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodeinDiServerVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // Tests
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}