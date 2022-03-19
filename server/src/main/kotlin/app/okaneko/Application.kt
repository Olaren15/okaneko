package app.okaneko

import app.okaneko.plugin.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureDependencyInjection()
    //configureAuthentication()
    configureSerialisation()
    configureRouting()
}
