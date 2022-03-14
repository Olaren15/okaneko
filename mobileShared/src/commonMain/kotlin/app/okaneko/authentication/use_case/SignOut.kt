package app.okaneko.authentication.use_case

interface SignOut {
    suspend operator fun invoke()
}