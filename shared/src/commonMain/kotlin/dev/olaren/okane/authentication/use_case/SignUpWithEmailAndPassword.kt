package dev.olaren.okane.authentication.use_case

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.errors.SignUpError
import dev.olaren.okane.authentication.repositories.AuthenticationRepository

class SignUpWithEmailAndPassword(private val repository: AuthenticationRepository) {
    private val emailRegex =
        Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")

    private val passwordRegex =
        Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$ %^&*-]).{8,}\$")

    suspend operator fun invoke(
        email: String,
        password: String,
        confirmedPassword: String
    ): Result<User, SignUpError> {
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()
        val trimmedConfirmedPassword = confirmedPassword.trim()

        if (!trimmedEmail.matches(emailRegex)) {
            return Err(SignUpError.InvalidEmailError)
        }

        if (trimmedPassword != trimmedConfirmedPassword) {
            return Err(SignUpError.PasswordsNotMatchingError)
        }

        if (!trimmedPassword.matches(passwordRegex)) {
            return Err(SignUpError.PasswordTooWeakError)
        }

        return repository.signUpWithEmailAndPassword(trimmedEmail, trimmedPassword)
    }
}