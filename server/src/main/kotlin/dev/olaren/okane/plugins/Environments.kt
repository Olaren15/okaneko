package dev.olaren.okane.plugins

import dev.olaren.okane.util.envConfig.EnvConfig
import io.ktor.server.application.*

fun Application.configureEnvironment() {
    EnvConfig.initConfig(environment.config)
}