package app.okaneko.authentication.repository.implementation

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.data.entity.UserEntity
import app.okaneko.authentication.repository.UserRepository
import app.okaneko.database.repository.MongoRepository
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoUserRepository(db: CoroutineDatabase) : UserRepository, MongoRepository<UserEntity, User> {
    override val collection: CoroutineCollection<UserEntity> = db.getCollection()
}