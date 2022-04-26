package app.okaneko.authentication.repository.implementation

import app.okaneko.authentication.repository.RefreshTokenRepository
import app.okaneko.database.repository.implementation.RedisRepository
import org.vitalyros.redisson.kotlin.coroutines.RedissonCoroutinesClient

class RedisRefreshTokenRepository(
    override val client: RedissonCoroutinesClient,
    userId: String,
    refreshToken: String,
) :
    RefreshTokenRepository, RedisRepository {
    override val key: String = "$userId:$refreshToken"
}