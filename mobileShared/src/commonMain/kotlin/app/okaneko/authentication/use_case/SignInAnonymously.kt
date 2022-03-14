package app.okaneko.authentication.use_case

import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.errors.SignInError
import com.github.michaelbull.result.Result

interface SignInAnonymously {
    suspend operator fun invoke(): Result<User, SignInError>
}