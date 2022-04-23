package app.okaneko.user.use_case.implementation

import app.okaneko.user.use_case.HashUserPassword
import at.favre.lib.crypto.bcrypt.BCrypt

class HashUserPasswordBcryptImpl : HashUserPassword {
    override suspend fun invoke(password: String): String {
        return BCrypt.with(BCrypt.Version.VERSION_2A).hashToString(12, password.toCharArray())
    }
}