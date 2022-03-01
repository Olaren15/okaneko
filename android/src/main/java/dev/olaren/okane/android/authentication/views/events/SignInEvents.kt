package dev.olaren.okane.android.authentication.views.events

import androidx.compose.ui.text.input.TextFieldValue

sealed class SignInEvents {
    data class EnteredEmail(val value: TextFieldValue) : SignInEvents()
    data class EnteredPassword(val value: TextFieldValue) : SignInEvents()
    object PressedSignInButton : SignInEvents()
}