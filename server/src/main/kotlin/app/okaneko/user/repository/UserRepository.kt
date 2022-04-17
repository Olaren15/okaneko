package app.okaneko.user.repository

import app.okaneko.database.error.EntityNotFoundError
import app.okaneko.database.repository.EntityRepository
import app.okaneko.user.data.dto.User
import app.okaneko.user.data.entity.UserEntity
import com.github.michaelbull.result.Result

interface UserRepository : EntityRepository<UserEntity, User> {
    suspend fun getUserByEmail(email: String): Result<UserEntity, EntityNotFoundError>
}