package dev.olaren.okane

import dev.olaren.okane.plugins.configureAuthentication
import dev.olaren.okane.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureAuthentication()
    configureRouting()
}
