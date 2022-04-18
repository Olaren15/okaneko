package app.okaneko.authentication.use_case

interface IsUserLoggedIn {
    suspend operator fun invoke(): Boolean
}