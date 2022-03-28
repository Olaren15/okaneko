package app.okaneko.authentication.data.validator

data class AuthenticationValidators(
    val emailValidator: EmailValidator,
    val passwordValidator: PasswordValidator,
)