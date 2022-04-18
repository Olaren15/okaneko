package app.okaneko.authentication.errors

sealed class SignInError {
    object InvalidCredentialsError : SignInError()
    object UnknownError : SignInError()
}