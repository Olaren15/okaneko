package app.okaneko.group.use_case

import app.okaneko.group.data.dto.Group
import app.okaneko.group.error.GetGroupError
import com.github.michaelbull.result.Result
import java.util.UUID

interface GetGroupById {
    suspend operator fun invoke(groupId: UUID, userId: String): Result<Group, GetGroupError>
}