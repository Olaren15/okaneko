package app.okaneko.group.data.entity

import app.okaneko.database.data.entity.Entity
import app.okaneko.group.data.dto.Group
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class GroupEntity(
    @Contextual
    @SerialName("_id")
    override var id: UUID?,
    val usersIds: List<String>,
    val name: String,
    override val createdAt: Instant?,
    override var updatedAt: Instant?,
) : Entity<Group> {
    override fun toDto(): Group {
        return Group(
            id = this.id!!.toString(),
            usersIds = this.usersIds,
            name = this.name,
            createdAt = this.createdAt ?: Clock.System.now(),
            updatedAt = this.updatedAt ?: Clock.System.now(),
        )
    }
}