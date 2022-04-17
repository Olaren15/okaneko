package app.okaneko.group.repository.implementation

import app.okaneko.database.repository.implementation.MongoRepository
import app.okaneko.group.data.dto.Group
import app.okaneko.group.data.entity.GroupEntity
import app.okaneko.group.repository.GroupRepository
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoGroupRepository(db: CoroutineDatabase) : GroupRepository, MongoRepository<GroupEntity, Group> {
    override var collection: CoroutineCollection<GroupEntity> = db.getCollection()

    override suspend fun getGroupsByUserId(userId: String): List<GroupEntity> {
        return collection.find("{ usersIds: {\$in: [ \"$userId\" ] } }").toList()
    }
}