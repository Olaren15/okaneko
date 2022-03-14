package app.okaneko.group.use_cases.implementation

import app.okaneko.group.data.dto.Group
import app.okaneko.group.errors.GetGroupError
import app.okaneko.group.use_cases.GetGroupById
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class GetGroupByIdImpl : GetGroupById {
    override fun invoke(groupId: String, userId: String): Result<Group, GetGroupError> {
        // TODO: Add proper implementation
        return Ok(Group(groupId, listOf(userId), "Group Name"))
    }
}