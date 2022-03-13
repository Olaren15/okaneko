package dev.olaren.okane.group.use_cases

import dev.olaren.okane.group.data.dto.Group
import com.github.michaelbull.result.Result
import dev.olaren.okane.group.errors.GetGroupError

interface GetGroupById {
    operator fun invoke(groupId: String, userId: String): Result<Group, GetGroupError>
}