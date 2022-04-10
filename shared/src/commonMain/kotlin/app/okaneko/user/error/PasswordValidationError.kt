package app.okaneko.user.error

sealed class PasswordValidationError {
    object AtLeastEightCharacters : PasswordValidationError()
    object AtLeastOneUpperCaseLetter : PasswordValidationError()
    object AtLeastOneLowerCaseLetter : PasswordValidationError()
    object AtLeastOneSpecialCharacter : PasswordValidationError()
}