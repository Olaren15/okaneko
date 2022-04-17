package app.okaneko.database.di

import io.ktor.server.config.*
import org.kodein.di.*
import org.redisson.Redisson
import org.redisson.config.Config
import org.vitalyros.redisson.kotlin.coroutines

val redisServerModule = DI.Module("RedisServerModule") {
    bind {
        singleton {
            val appConfig: ApplicationConfig = instance()

            val redisConfig = Config()
            redisConfig.useSingleServer().address = appConfig.property("redis.connectionUrl").getString()

            redisConfig
        }
    }

    bind {
        provider {
            Redisson.create().reactive().coroutines()
        }
    }
}