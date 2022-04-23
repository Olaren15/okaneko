package app.okaneko.user.use_case.implementation

import app.okaneko.user.use_case.VerifyUserPassword
import at.favre.lib.crypto.bcrypt.BCrypt

class VerifyUserPasswordBcryptImpl : VerifyUserPassword {
    override suspend fun invoke(password: String, hashedPassword: String): Boolean {
        return BCrypt.verifyer(BCrypt.Version.VERSION_2A)
            .verifyStrict(password.toCharArray(), hashedPassword.toCharArray()).verified
    }
}