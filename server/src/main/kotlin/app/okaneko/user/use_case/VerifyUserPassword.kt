package app.okaneko.user.use_case

interface VerifyUserPassword {
    suspend operator fun invoke(password: String, hashedPassword: String): Boolean
}