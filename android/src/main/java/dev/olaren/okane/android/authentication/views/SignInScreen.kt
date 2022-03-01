package dev.olaren.okane.android.authentication.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.olaren.okane.android.Routes
import dev.olaren.okane.android.authentication.viewmodels.SignInViewModel
import dev.olaren.okane.android.authentication.views.components.EmailField
import dev.olaren.okane.android.authentication.views.components.PasswordField
import dev.olaren.okane.android.authentication.views.events.SignInEvents
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignIn(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {
    val context = LocalContext.current

    var textFieldInError by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is SignInViewModel.UiEvent.SignedIn -> {
                    textFieldInError = false

                    navController.navigate(Routes.Groups.route) {
                        popUpTo(Routes.SignIn.route) {
                            inclusive = true
                        }
                    }
                }

                is SignInViewModel.UiEvent.SignInError -> {
                    textFieldInError = false
                    Toast.makeText(
                        context,
                        "An error occured while signin in",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is SignInViewModel.UiEvent.InvalidCredentials -> {
                    textFieldInError = true
                    Toast.makeText(
                        context,
                        "The provided credentials are invalid",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            emailValue = viewModel.email.value,
            onEmailChange = { viewModel.onEvent(SignInEvents.EnteredEmail(it)) },
            textFieldInError
        )
        Spacer(modifier = Modifier.height(5.dp))
        PasswordField(
            passwordValue = viewModel.password.value,
            onPasswordChange = { viewModel.onEvent(SignInEvents.EnteredPassword(it)) },
            textFieldInError
        )

        Button(onClick = { viewModel.onEvent(SignInEvents.PressedSignInButton) }) {
            Text("Sign In")
        }

        Button(onClick = {
            navController.navigate(Routes.SignUp.route) {
                popUpTo(Routes.SignIn.route) {
                    inclusive = true
                }
            }
        }) {
            Text("Don't have an acoount? Sign Up!")
        }
    }
}