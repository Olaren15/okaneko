package dev.olaren.okane.group.use_cases.implementation

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.olaren.okane.group.data.dto.Group
import dev.olaren.okane.group.errors.GetGroupError
import dev.olaren.okane.group.use_cases.GetGroupById

class GetGroupByIdImpl : GetGroupById {
    override fun invoke(groupId: String, userId: String): Result<Group, GetGroupError> {
        // TODO: Add proper implementation
        return Ok(Group(groupId, listOf(userId), "Group Name"))
    }
}