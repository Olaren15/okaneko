package app.okaneko.plugin

import app.okaneko.authentication.di.authenticationServerModule
import app.okaneko.database.di.mongoServerModule
import app.okaneko.database.di.redisServerModule
import app.okaneko.di.sharedModule
import app.okaneko.group.di.groupsServerModule
import app.okaneko.user.di.userServerModule
import io.ktor.server.application.*
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

fun Application.configureDependencyInjection() {
    di {
        bind {
            singleton {
                environment.config
            }
        }

        bind {
            singleton {
                log
            }
        }

        import(mongoServerModule)
        import(redisServerModule)
        import(sharedModule)
        import(authenticationServerModule)
        import(userServerModule)
        import(groupsServerModule)
    }
}