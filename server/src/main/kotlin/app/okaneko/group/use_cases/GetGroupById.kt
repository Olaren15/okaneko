package app.okaneko.group.use_cases

import app.okaneko.group.data.dto.Group
import app.okaneko.group.errors.GetGroupError
import com.github.michaelbull.result.Result

interface GetGroupById {
    operator fun invoke(groupId: String, userId: String): Result<Group, GetGroupError>
}