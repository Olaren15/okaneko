package app.okaneko.group.repository

import app.okaneko.database.repository.EntityRepository
import app.okaneko.group.data.dto.Group
import app.okaneko.group.data.entity.GroupEntity

interface GroupRepository : EntityRepository<GroupEntity, Group> {
    suspend fun getGroupsByUserId(userId: String): List<GroupEntity>
}