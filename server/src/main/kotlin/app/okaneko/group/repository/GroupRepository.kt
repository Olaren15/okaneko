package app.okaneko.group.repository

import app.okaneko.database.repository.Repository
import app.okaneko.group.data.dto.Group
import app.okaneko.group.data.entity.GroupEntity

interface GroupRepository : Repository<GroupEntity, Group> {
    suspend fun getGroupsByUserId(userId: String): List<GroupEntity>
}