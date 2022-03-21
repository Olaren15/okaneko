package app.okaneko.database.repository

import app.okaneko.base.data.dto.Dto
import app.okaneko.database.data.entity.Entity
import app.okaneko.database.error.EntityNotCreatedError
import app.okaneko.database.error.EntityNotDeletedError
import app.okaneko.database.error.EntityNotFoundError
import app.okaneko.database.error.EntityNotUpdatedError
import com.github.michaelbull.result.Result
import java.util.UUID

interface Repository<T : Entity<U>, U : Dto> {
    suspend fun getById(id: UUID): Result<T, EntityNotFoundError>

    suspend fun getAll(): List<T>

    suspend fun deleteById(id: UUID): Result<T, EntityNotDeletedError>

    suspend fun insert(entity: T): Result<T, EntityNotCreatedError>

    suspend fun update(entity: T): Result<T, EntityNotUpdatedError>
}