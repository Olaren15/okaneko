package app.okaneko.plugin

import app.okaneko.authentication.di.authenticationServerModule
import app.okaneko.group.di.groupsServerModule
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import io.ktor.server.application.*
import org.bson.UuidRepresentation
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.ktor.di
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureDependencyInjection() {
    di {
        import(authenticationServerModule)
        import(groupsServerModule)

        bind {
            // try to connect as soon as the server launches
            eagerSingleton {
                val connectionString = ConnectionString(environment.config.property("mongo.connectionUrl").getString())
                val databaseName =
                    connectionString.database ?: environment.config.property("mongo.database").getString()

                val client = KMongo.createClient(
                    MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .uuidRepresentation(UuidRepresentation.STANDARD)
                        .build()
                ).coroutine

                client.getDatabase(databaseName)
            }
        }
    }
}