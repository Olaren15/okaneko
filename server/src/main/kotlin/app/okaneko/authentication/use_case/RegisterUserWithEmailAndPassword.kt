package app.okaneko.authentication.use_case

import app.okaneko.authentication.data.dto.EmailPasswordRegistration
import app.okaneko.authentication.data.dto.User
import app.okaneko.authentication.error.RegisterUserWithEmailAndPasswordError
import com.github.michaelbull.result.Result

interface RegisterUserWithEmailAndPassword {
    suspend operator fun invoke(registration: EmailPasswordRegistration): Result<User, RegisterUserWithEmailAndPasswordError>
}