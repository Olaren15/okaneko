package app.okaneko.authentication.repository.implementation

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.data.entity.UserEntity
import app.okaneko.authentication.repository.UserRepository
import app.okaneko.database.error.EntityNotFoundError
import app.okaneko.database.repository.MongoRepository
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.toResultOr
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MongoUserRepository(db: CoroutineDatabase) : UserRepository, MongoRepository<UserEntity, User> {
    override val collection: CoroutineCollection<UserEntity> = db.getCollection()

    override suspend fun getUserByEmail(email: String): Result<UserEntity, EntityNotFoundError> {
        return collection.findOne(UserEntity::email eq email)
            .toResultOr { EntityNotFoundError }
    }
}