package dev.olaren.okane.authentication.use_case

import com.github.michaelbull.result.Result
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.errors.SignUpError

interface SignUpWithEmailAndPassword {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmedPassword: String
    ): Result<User, SignUpError>
}