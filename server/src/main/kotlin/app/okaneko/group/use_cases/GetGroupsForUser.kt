package app.okaneko.group.use_cases

import app.okaneko.authentication.data.dto.User
import app.okaneko.group.data.dto.Group

interface GetGroupsForUser {
    suspend operator fun invoke(user: User): List<Group>
}