package app.okaneko.plugins

import app.okaneko.util.envConfig.EnvConfig
import io.ktor.server.application.*

fun Application.configureEnvironment() {
    EnvConfig.initConfig(environment.config)
}