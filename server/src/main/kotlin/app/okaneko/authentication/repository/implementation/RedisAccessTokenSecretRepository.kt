package app.okaneko.authentication.repository.implementation

import app.okaneko.authentication.repository.AccessTokenSecretRepository
import app.okaneko.database.repository.implementation.RedisRepository
import org.vitalyros.redisson.kotlin.coroutines.RedissonCoroutinesClient

class RedisAccessTokenSecretRepository(override val client: RedissonCoroutinesClient) :
    AccessTokenSecretRepository, RedisRepository