package app.okaneko.database.repository

import app.okaneko.base.data.dto.Dto
import app.okaneko.database.error.EntityNotCreatedError
import app.okaneko.database.error.EntityNotDeletedError
import app.okaneko.database.error.EntityNotFoundError
import app.okaneko.database.error.EntityNotUpdatedError
import com.github.michaelbull.result.Result

interface Repository<T : Dto> {
    suspend fun getById(id: String): Result<T, EntityNotFoundError>

    suspend fun getAll(): List<T>

    suspend fun deleteById(id: String): Result<T, EntityNotDeletedError>

    suspend fun insert(dto: T): Result<T, EntityNotCreatedError>

    suspend fun update(dto: T): Result<T, EntityNotUpdatedError>
}