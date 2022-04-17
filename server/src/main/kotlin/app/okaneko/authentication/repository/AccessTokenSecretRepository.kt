package app.okaneko.authentication.repository

import app.okaneko.database.repository.KeyValueRepository

interface AccessTokenSecretRepository : KeyValueRepository {
    override val key: String
        get() = "okaneko_access_token_secret_key"
}