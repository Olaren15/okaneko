package app.okaneko.database.repository.implementation

import app.okaneko.database.error.KeyNotDeletedError
import app.okaneko.database.error.KeyNotFoundError
import app.okaneko.database.error.KeyNotUpdatedError
import app.okaneko.database.repository.KeyValueRepository
import com.github.michaelbull.result.*
import org.vitalyros.redisson.kotlin.coroutines.RedissonCoroutinesClient

interface RedisRepository : KeyValueRepository {
    val client: RedissonCoroutinesClient

    override suspend fun getValue(): Result<String, KeyNotFoundError> {
        return client.getBucket<String>(key).get()
            .toResultOr { KeyNotFoundError }
    }

    override suspend fun isValuePresent(): Boolean {
        return client.getBucket<String>(key).isExists()
    }

    override suspend fun setValue(value: String): Result<String, KeyNotUpdatedError> {
        val bucket = client.getBucket<String>(key)
        bucket.set(value)

        return bucket.get()
            .toResultOr { KeyNotUpdatedError }
            .andThen {
                return if (it == value)
                    Ok(value)
                else
                    Err(KeyNotUpdatedError)
            }
    }

    override suspend fun deleteValue(): Result<Unit, KeyNotDeletedError> {
        return client.getBucket<String>(key).delete().let {
            if (it)
                Ok(Unit)
            else
                Err(KeyNotDeletedError)
        }
    }
}