package dev.olaren.okane.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*

fun Application.configureSerialisation() {
    install(ContentNegotiation) {
        json()
    }
}