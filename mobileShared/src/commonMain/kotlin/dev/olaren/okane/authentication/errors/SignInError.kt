package dev.olaren.okane.authentication.errors

sealed class SignInError {
    object InvalidCredentialsError : SignInError()
    object UnknownError : SignInError()
}