package dev.olaren.okane.authentication.use_case

import com.github.michaelbull.result.Result
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.errors.SignInError

interface SignInWithEmailAndPassword {
    suspend operator fun invoke(email: String, password: String): Result<User, SignInError>
}