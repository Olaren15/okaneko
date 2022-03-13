package dev.olaren.okane.plugins

import dev.olaren.okane.group.di.groupsServerModule
import io.ktor.server.application.*
import org.kodein.di.ktor.di

fun Application.configureDependencyInjection() {
    di {
        import(groupsServerModule)
    }
}