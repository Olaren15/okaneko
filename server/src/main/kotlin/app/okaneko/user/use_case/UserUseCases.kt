package app.okaneko.user.use_case

data class UserUseCases(
    val registerUserWithEmailAndPassword: RegisterUserWithEmailAndPassword,
    val getUserIdFromEmailAndPassword: GetUserIdFromEmailAndPassword,
)