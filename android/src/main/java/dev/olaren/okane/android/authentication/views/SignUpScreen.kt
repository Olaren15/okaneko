package dev.olaren.okane.android.authentication.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import dev.olaren.okane.android.Routes

@Composable
fun SignUp(navController: NavController) {
    Column {
        var email by remember {
            mutableStateOf(TextFieldValue(""))
        }

        var password by remember {
            mutableStateOf(TextFieldValue(""))
        }

        TextField(value = email, onValueChange = { newEmail -> email = newEmail })

        TextField(value = password, onValueChange = { newPassword -> password = newPassword })

        Button(onClick = { navController.navigate(Routes.Groups.route) }) {
            Text("Sign Up")
        }

        Button(onClick = { navController.navigate(Routes.SignIn.route) }) {
            Text("Already have an account?")
        }
    }
}