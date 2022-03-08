package dev.olaren.okane.authentication.use_case

import com.github.michaelbull.result.Result
import dev.olaren.okane.authentication.data.dto.User
import dev.olaren.okane.authentication.errors.SignInError

interface SignInAnonymously {
    suspend operator fun invoke(): Result<User, SignInError>
}