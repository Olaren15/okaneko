package app.okaneko.group.use_case

import app.okaneko.group.data.dto.Group
import app.okaneko.group.error.GetGroupError
import app.okaneko.user.data.dto.User
import com.github.michaelbull.result.Result
import java.util.*

interface GetGroupById {
    suspend operator fun invoke(groupId: UUID, user: User): Result<Group, GetGroupError>
}