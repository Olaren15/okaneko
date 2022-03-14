package app.okaneko.android.authentication.views.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    passwordValue: TextFieldValue = TextFieldValue(""),
    label: String = "Password",
    placeholder: String = "Password",
    onPasswordChange: (TextFieldValue) -> Unit = {},
    isError: Boolean = false,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        value = passwordValue,
        onValueChange = onPasswordChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = true,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        modifier = modifier,
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
    PasswordField(passwordValue = TextFieldValue("This is a password"))
}