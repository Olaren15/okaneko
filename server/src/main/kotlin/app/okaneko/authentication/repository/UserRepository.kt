package app.okaneko.authentication.repository

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.data.entity.UserEntity
import app.okaneko.database.error.EntityNotFoundError
import app.okaneko.database.repository.Repository
import com.github.michaelbull.result.Result

interface UserRepository : Repository<UserEntity, User> {
    suspend fun getUserByEmail(email: String): Result<UserEntity, EntityNotFoundError>
}