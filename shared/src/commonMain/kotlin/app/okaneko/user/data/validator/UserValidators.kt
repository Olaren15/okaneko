package app.okaneko.user.data.validator

data class UserValidators(
    val emailValidator: EmailValidator,
    val passwordValidator: PasswordValidator,
)