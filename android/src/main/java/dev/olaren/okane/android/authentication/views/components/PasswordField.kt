package dev.olaren.okane.android.authentication.views.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordField(
    passwordValue: TextFieldValue,
    onPasswordChange: (TextFieldValue) -> Unit,
    isError: Boolean
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        value = passwordValue,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        placeholder = { Text("Password") },
        singleLine = true,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = isError,
        trailingIcon = {
            val image = if (passwordVisibility)
                Icons.Filled.Visibility
            else
                Icons.Filled.VisibilityOff

            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(imageVector = image, "Toggle password visibility")
            }
        },
    )
}

@Preview
@Composable
fun PreviewPassword() {
    PasswordField(
        passwordValue = TextFieldValue("This is a password"),
        onPasswordChange = {},
        false
    )
}