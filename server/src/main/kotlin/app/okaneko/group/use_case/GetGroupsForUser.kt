package app.okaneko.group.use_case

import app.okaneko.group.data.dto.Group
import app.okaneko.user.data.dto.User

interface GetGroupsForUser {
    suspend operator fun invoke(user: User): List<Group>
}