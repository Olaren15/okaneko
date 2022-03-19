package app.okaneko.database.repositories

import app.okaneko.base.data.dto.Dto
import app.okaneko.database.data.entity.MongoEntity
import app.okaneko.database.data.entity.dtoToEntity
import app.okaneko.database.errors.EntityNotCreatedError
import app.okaneko.database.errors.EntityNotDeletedError
import app.okaneko.database.errors.EntityNotFoundError
import app.okaneko.database.errors.EntityNotUpdatedError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.datetime.Clock
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.id.WrappedObjectId

interface MongoRepository<T : MongoEntity<U>, U : Dto> : Repository<U> {
    val collection: CoroutineCollection<T>

    fun updateTimestamp(entity: T) {
        entity.updatedAt = Clock.System.now()
    }

    override suspend fun getById(id: String): Result<U, EntityNotFoundError> {
        return collection.findOneById(WrappedObjectId<T>(id))?.let {
            Ok(it.toDto())
        } ?: run {
            Err(EntityNotFoundError)
        }
    }

    override suspend fun getAll(): List<U> {
        return collection.find().toList().map {
            it.toDto()
        }
    }

    override suspend fun deleteById(id: String): Result<U, EntityNotDeletedError> {
        return collection.findOneAndDelete(MongoEntity<U>::_id eq WrappedObjectId<T>(id))?.let {
            Ok(it.toDto())
        } ?: run {
            Err(EntityNotDeletedError)
        }
    }

    override suspend fun insert(dto: U): Result<U, EntityNotCreatedError> {
        @Suppress("UNCHECKED_CAST")
        val mongoEntity = dtoToEntity(dto) as T

        return try {
            val insertResult = collection.insertOne(mongoEntity)

            if (insertResult.wasAcknowledged()) {
                Ok(collection.findOneById(insertResult.insertedId!!)!!.toDto())
            } else {
                Err(EntityNotCreatedError)
            }
        } catch (e: Throwable) {
            Err(EntityNotCreatedError)
        }
    }

    override suspend fun update(dto: U): Result<U, EntityNotUpdatedError> {
        @Suppress("UNCHECKED_CAST")
        val mongoEntity: T = dtoToEntity(dto) as T

        updateTimestamp(mongoEntity)

        return try {
            val updateResult = collection.updateOne(MongoEntity<U>::_id eq mongoEntity._id, mongoEntity)

            if (updateResult.wasAcknowledged()) {
                Ok(collection.findOneById(updateResult.upsertedId!!)!!.toDto())
            } else {
                Err(EntityNotUpdatedError)
            }
        } catch (e: Throwable) {
            Err(EntityNotUpdatedError)
        }
    }
}