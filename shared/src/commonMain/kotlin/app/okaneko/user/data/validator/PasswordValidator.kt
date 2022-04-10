package app.okaneko.user.data.validator

import app.okaneko.user.error.PasswordValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class PasswordValidator {
    private val upperCaseLetterRegex = Regex(".*[A-Z].*")
    private val lowerCaseLetterRegex = Regex(".*[a-z].*")
    private val specialCharacterRegex = Regex(".*[#?!@\$%^&*-].*")

    operator fun invoke(password: String): Result<Unit, List<PasswordValidationError>> {
        val errors = arrayListOf<PasswordValidationError>()

        if (password.length < 8)
            errors.add(PasswordValidationError.AtLeastEightCharacters)

        if (!password.matches(upperCaseLetterRegex))
            errors.add(PasswordValidationError.AtLeastOneUpperCaseLetter)

        if (!password.matches(lowerCaseLetterRegex))
            errors.add(PasswordValidationError.AtLeastOneLowerCaseLetter)

        if (!password.matches(specialCharacterRegex))
            errors.add(PasswordValidationError.AtLeastOneSpecialCharacter)

        return if (errors.isEmpty())
            Ok(Unit)
        else
            Err(errors)
    }
}