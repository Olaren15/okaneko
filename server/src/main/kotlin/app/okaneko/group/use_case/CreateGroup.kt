package app.okaneko.group.use_case

import app.okaneko.authentication.data.dto.User
import app.okaneko.group.data.dto.Group
import app.okaneko.group.data.dto.GroupCreation
import app.okaneko.group.error.CreateGroupError
import com.github.michaelbull.result.Result

interface CreateGroup {
    suspend operator fun invoke(newGroup: GroupCreation, user: User): Result<Group, CreateGroupError>
}