package app.okaneko.plugins

import app.okaneko.group.di.groupsServerModule
import io.ktor.server.application.*
import org.kodein.di.ktor.di

fun Application.configureDependencyInjection() {
    di {
        import(groupsServerModule)
    }
}