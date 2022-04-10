package app.okaneko.group.use_case.implementation

import app.okaneko.group.data.dto.Group
import app.okaneko.group.error.GetGroupError
import app.okaneko.group.repository.GroupRepository
import app.okaneko.group.use_case.GetGroupById
import app.okaneko.user.data.dto.User
import com.github.michaelbull.result.*
import java.util.*

class GetGroupByIdImpl(private val repository: GroupRepository) : GetGroupById {
    override suspend fun invoke(groupId: UUID, user: User): Result<Group, GetGroupError> {
        return repository.getById(groupId)
            .mapError {
                GetGroupError.IdNotFound
            }
            .andThen {
                return if (it.usersIds.contains(user.id)) {
                    Ok(it.toDto())
                } else {
                    Err(GetGroupError.NotPermittedError)
                }
            }
    }
}