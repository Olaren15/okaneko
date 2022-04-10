package app.okaneko.user.use_case

import app.okaneko.user.data.dto.EmailPasswordRegistration
import app.okaneko.user.data.dto.User
import app.okaneko.user.error.RegisterUserWithEmailAndPasswordError
import com.github.michaelbull.result.Result

interface RegisterUserWithEmailAndPassword {
    suspend operator fun invoke(registration: EmailPasswordRegistration): Result<User, RegisterUserWithEmailAndPasswordError>
}