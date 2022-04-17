package app.okaneko.database.di

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import io.ktor.server.config.*
import org.bson.UuidRepresentation
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mongoServerModule = DI.Module("MongoServerModule") {
    bind {
        // try to connect as soon as the server launches
        eagerSingleton {
            val appConfig: ApplicationConfig = instance()
            val connectionString = ConnectionString(appConfig.property("mongo.connectionUrl").getString())
            val databaseName = connectionString.database ?: "okaneko"

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