package app.okaneko.group.data.entity

import app.okaneko.database.data.entity.MongoEntity
import app.okaneko.group.data.dto.Group
import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id

@Serializable
data class GroupEntity(
    @Contextual override val _id: Id<GroupEntity>,
    val usersIds: List<String>,
    val name: String,
    override val createdAt: Instant,
    override var updatedAt: Instant,
) : MongoEntity<Group> {
    override fun toDto(): Group {
        return Group(
            id = this._id.toString(),
            usersIds = this.usersIds,
            name = this.name,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}