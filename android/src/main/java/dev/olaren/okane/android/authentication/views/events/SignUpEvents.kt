package dev.olaren.okane.android.authentication.views.events

import androidx.compose.ui.text.input.TextFieldValue

sealed class SignUpEvents {
    data class EnteredEmail(val value: TextFieldValue) : SignUpEvents()
    data class EnteredPassword(val value: TextFieldValue) : SignUpEvents()
    data class EnteredConfirmedPassword(val value: TextFieldValue) : SignUpEvents()
    object SignUpButtonPressed : SignUpEvents()
}