package app.okaneko.authentication.repository

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.data.entity.UserEntity
import app.okaneko.database.repository.Repository

interface UserRepository : Repository<UserEntity, User>