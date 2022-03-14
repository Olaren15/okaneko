package app.okaneko

import app.okaneko.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureEnvironment()
    configureDependencyInjection()
    configureAuthentication()
    configureSerialisation()
    configureRouting()
}
