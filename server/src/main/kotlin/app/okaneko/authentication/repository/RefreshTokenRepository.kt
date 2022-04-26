package app.okaneko.authentication.repository

import app.okaneko.database.repository.KeyValueRepository

interface RefreshTokenRepository : KeyValueRepository {
    override val key: String
}