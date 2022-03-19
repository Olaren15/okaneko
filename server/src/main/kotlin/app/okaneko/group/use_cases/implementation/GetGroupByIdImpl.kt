package app.okaneko.group.use_cases.implementation

import app.okaneko.group.data.dto.Group
import app.okaneko.group.errors.GetGroupError
import app.okaneko.group.repositories.GroupRepository
import app.okaneko.group.use_cases.GetGroupById
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.get

class GetGroupByIdImpl(private val repository: GroupRepository) : GetGroupById {
    override suspend fun invoke(groupId: String, userId: String): Result<Group, GetGroupError> {
        val group = repository.getById(groupId).get() ?: return Err(GetGroupError.IdNotFound)

        return if (group.usersIds.contains(userId)) {
            Ok(group)
        } else {
            Err(GetGroupError.NotPermittedError)
        }
    }
}