package app.okaneko.database.repository

import app.okaneko.database.error.KeyNotDeletedError
import app.okaneko.database.error.KeyNotFoundError
import app.okaneko.database.error.KeyNotUpdatedError
import com.github.michaelbull.result.Result

interface KeyValueRepository {
    val key: String

    suspend fun getValue(): Result<String, KeyNotFoundError>

    suspend fun isValuePresent(): Boolean

    suspend fun setValue(value: String): Result<String, KeyNotUpdatedError>

    suspend fun deleteValue(): Result<Unit, KeyNotDeletedError>
}