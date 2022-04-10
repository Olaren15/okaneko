package app.okaneko.group.use_case.implementation

import app.okaneko.group.data.dto.Group
import app.okaneko.group.repository.GroupRepository
import app.okaneko.group.use_case.GetGroupsForUser
import app.okaneko.user.data.dto.User

class GetGroupsForUserImpl(private val repository: GroupRepository) : GetGroupsForUser {
    override suspend fun invoke(user: User): List<Group> {
        return repository.getGroupsByUserId(user.id).map {
            it.toDto()
        }
    }
}