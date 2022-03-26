package app.okaneko.database.repository

import app.okaneko.base.data.dto.Dto
import app.okaneko.database.data.entity.Entity
import app.okaneko.database.error.EntityNotCreatedError
import app.okaneko.database.error.EntityNotDeletedError
import app.okaneko.database.error.EntityNotFoundError
import app.okaneko.database.error.EntityNotUpdatedError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.toResultOr
import kotlinx.datetime.Clock
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import java.util.*

interface MongoRepository<T : Entity<U>, U : Dto> : Repository<T, U> {
    val collection: CoroutineCollection<T>

    fun updateTimestamp(entity: T) {
        entity.updatedAt = Clock.System.now()
    }

    override suspend fun getById(id: UUID): Result<T, EntityNotFoundError> {
        return collection.findOneById(id)
            .toResultOr { EntityNotFoundError }
    }

    override suspend fun getAll(): List<T> {
        return collection.find().toList()
    }

    override suspend fun deleteById(id: UUID): Result<T, EntityNotDeletedError> {
        return collection.findOneAndDelete(Entity<U>::id eq id)
            .toResultOr { EntityNotDeletedError }
    }

    override suspend fun insert(entity: T): Result<T, EntityNotCreatedError> {
        entity.id = UUID.randomUUID()

        return try {
            val insertResult = collection.insertOne(entity)

            if (insertResult.wasAcknowledged()) {
                Ok(collection.findOneById(insertResult.insertedId!!)!!)
            } else {
                Err(EntityNotCreatedError)
            }
        } catch (e: Throwable) {
            Err(EntityNotCreatedError)
        }
    }

    override suspend fun update(entity: T): Result<T, EntityNotUpdatedError> {
        updateTimestamp(entity)

        return try {
            val updateResult = collection.updateOne(Entity<U>::id eq entity.id, entity)

            if (updateResult.wasAcknowledged()) {
                Ok(collection.findOneById(updateResult.upsertedId!!)!!)
            } else {
                Err(EntityNotUpdatedError)
            }
        } catch (e: Throwable) {
            Err(EntityNotUpdatedError)
        }
    }
}