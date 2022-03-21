package app.okaneko.group.use_case.implementation

import app.okaneko.authentication.data.dto.User
import app.okaneko.group.data.dto.Group
import app.okaneko.group.data.dto.GroupCreation
import app.okaneko.group.data.entity.GroupEntity
import app.okaneko.group.error.CreateGroupError
import app.okaneko.group.repository.GroupRepository
import app.okaneko.group.use_case.CreateGroup
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import kotlinx.datetime.Clock

class CreateGroupImpl(private val repository: GroupRepository) : CreateGroup {
    override suspend fun invoke(
        newGroup: GroupCreation,
        user: User
    ): Result<Group, CreateGroupError> {
        val group = GroupEntity(
            id = null,
            usersIds = listOf(user.id),
            name = newGroup.name,
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        )

        return repository.insert(group).mapBoth(
            success = {
                Ok(it.toDto())
            },
            failure = {
                Err(CreateGroupError.CreationError)
            }
        )
    }
}