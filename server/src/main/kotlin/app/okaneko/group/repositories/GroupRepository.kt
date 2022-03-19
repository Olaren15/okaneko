package app.okaneko.group.repositories

import app.okaneko.database.repositories.Repository
import app.okaneko.group.data.dto.Group

interface GroupRepository : Repository<Group> {
    suspend fun getGroupsByUserId(userId: String): List<Group>
}