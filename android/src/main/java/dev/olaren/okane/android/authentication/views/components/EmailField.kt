package dev.olaren.okane.android.authentication.views.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    emailValue: TextFieldValue = TextFieldValue(""),
    onEmailChange: (TextFieldValue) -> Unit = {},
    isError: Boolean = false,
) {
    TextField(
        value = emailValue,
        onValueChange = onEmailChange,
        label = { Text("Email") },
        placeholder = { Text("Email") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = modifier,
        isError = isError,
    )
}

@Preview
@Composable
fun PreviewEmailField() {
    EmailField(emailValue = TextFieldValue("this.is.an@email.com"))
}