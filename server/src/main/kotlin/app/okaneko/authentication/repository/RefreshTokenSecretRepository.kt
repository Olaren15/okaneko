package app.okaneko.authentication.repository

import app.okaneko.database.repository.KeyValueRepository

interface RefreshTokenSecretRepository : KeyValueRepository {
    override val key: String
        get() = "okaneko_refresh_token_secret_key"
}