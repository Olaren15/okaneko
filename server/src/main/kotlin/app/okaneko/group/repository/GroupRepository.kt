package app.okaneko.group.repository

import app.okaneko.database.repository.Repository
import app.okaneko.group.data.dto.Group

interface GroupRepository : Repository<Group> {
    suspend fun getGroupsByUserId(userId: String): List<Group>
}