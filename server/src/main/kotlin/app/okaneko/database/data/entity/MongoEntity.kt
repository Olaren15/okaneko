package app.okaneko.database.data.entity

import app.okaneko.base.data.dto.Dto
import app.okaneko.group.data.dto.Group
import app.okaneko.group.data.entity.GroupEntity
import kotlinx.datetime.Instant
import org.litote.kmongo.Id
import org.litote.kmongo.id.WrappedObjectId
import org.litote.kmongo.newId

@Suppress("PropertyName")
interface MongoEntity<T : Dto> {
    val _id: Id<*>

    val createdAt: Instant
    var updatedAt: Instant

    fun toDto(): T
}

fun <T : Dto> dtoToEntity(dto: T): MongoEntity<T> {
    @Suppress("UNCHECKED_CAST")
    return when (dto) {
        is Group -> GroupEntity(
            _id = if (dto.id.isBlank()) newId() else WrappedObjectId(dto.id),
            usersIds = dto.usersIds,
            name = dto.name,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt,
        ) as MongoEntity<T>
        else -> throw NotImplementedError()
    }
}