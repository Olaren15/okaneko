package app.okaneko.user.use_case

interface HashUserPassword {
    suspend operator fun invoke(password: String): String
}