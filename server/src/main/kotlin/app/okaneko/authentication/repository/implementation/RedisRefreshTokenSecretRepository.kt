package app.okaneko.authentication.repository.implementation

import app.okaneko.authentication.repository.RefreshTokenSecretRepository
import app.okaneko.database.repository.implementation.RedisRepository
import org.vitalyros.redisson.kotlin.coroutines.RedissonCoroutinesClient

class RedisRefreshTokenSecretRepository(override val client: RedissonCoroutinesClient) : RedisRepository,
    RefreshTokenSecretRepository