package app.okaneko.android.authentication.views.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignInButtons(
    onSignInButtonClick: () -> Unit = {},
    onAnonymousSignInButtonClick: () -> Unit = {},
    onSignUpButtonClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            TextButton(
                onClick = onSignUpButtonClick,
                modifier = Modifier.padding(0.dp)
            ) {
                Text("Sign up")
            }

            TextButton(onClick = onAnonymousSignInButtonClick) {
                Text("Continue as guest")
            }
        }

        Button(onClick = onSignInButtonClick) {
            Text("Sign in")
        }
    }
}