package dev.olaren.okane.authentication.errors

sealed class SignUpError {
    object InvalidEmailError : SignUpError()
    object PasswordsNotMatchingError : SignUpError()
    object UserAlreadyExistsError : SignUpError()
    object PasswordTooWeakError : SignUpError()
    object UnknownError : SignUpError()
}