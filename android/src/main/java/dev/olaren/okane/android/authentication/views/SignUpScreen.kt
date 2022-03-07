package dev.olaren.okane.android.authentication.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.olaren.okane.android.Routes
import dev.olaren.okane.android.authentication.viewmodels.SignUpViewModel
import dev.olaren.okane.android.authentication.views.components.EmailField
import dev.olaren.okane.android.authentication.views.components.PasswordField
import dev.olaren.okane.android.authentication.views.events.SignUpEvents
import kotlinx.coroutines.flow.collectLatest
import org.kodein.di.compose.rememberViewModel

@Composable
fun SignUp(navController: NavController) {
    Column {
        val viewModel: SignUpViewModel by rememberViewModel()
        val context = LocalContext.current

        var emailFieldInError by remember { mutableStateOf(false) }
        var passwordFieldsInError by remember { mutableStateOf(false) }

        val passwordFocusRequester = FocusRequester()
        val confirmedPasswordFocusRequester = FocusRequester()

        LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest {
                when (it) {
                    is SignUpViewModel.UiEvent.InvalidEmailEntered,
                    is SignUpViewModel.UiEvent.UserAlreadyExists -> {
                        emailFieldInError = true
                        Toast.makeText(
                            context,
                            "The entered email is invalid",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is SignUpViewModel.UiEvent.PasswordDidNotMatch -> {
                        passwordFieldsInError = true
                        Toast.makeText(
                            context,
                            "The provided passwords do not match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is SignUpViewModel.UiEvent.PasswordIsTooWeak -> {
                        passwordFieldsInError = true
                        Toast.makeText(
                            context,
                            "The provided password does not meet the requirements",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is SignUpViewModel.UiEvent.SignedUp -> {
                        navController.navigate(Routes.Groups.route) {
                            popUpTo(Routes.SignUp.route) {
                                inclusive = true
                            }
                        }
                    }

                    SignUpViewModel.UiEvent.SignUpError -> {
                        emailFieldInError = true
                        passwordFieldsInError = true

                        Toast.makeText(
                            context,
                            "An error occured while signing up",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .width(400.dp)
                .padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailField(
                    modifier = Modifier.fillMaxWidth(),
                    emailValue = viewModel.email.value,
                    onEmailChange = { viewModel.handleEvent(SignUpEvents.EnteredEmail(it)) },
                    isError = emailFieldInError,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions {
                        passwordFocusRequester.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                PasswordField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
                    passwordValue = viewModel.password.value,
                    onPasswordChange = { viewModel.handleEvent(SignUpEvents.EnteredPassword(it)) },
                    isError = passwordFieldsInError,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions {
                        confirmedPasswordFocusRequester.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                PasswordField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(confirmedPasswordFocusRequester),
                    passwordValue = viewModel.confirmedPassword.value,
                    label = "Confirm password",
                    placeholder = "Confirm password",
                    onPasswordChange = {
                        viewModel.handleEvent(
                            SignUpEvents.EnteredConfirmedPassword(
                                it
                            )
                        )
                    },
                    isError = passwordFieldsInError,
                    imeAction = ImeAction.Go,
                    keyboardActions = KeyboardActions {
                        emailFieldInError = false
                        passwordFieldsInError = false
                        viewModel.handleEvent(SignUpEvents.SignUpButtonPressed)
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        emailFieldInError = false
                        passwordFieldsInError = false
                        viewModel.handleEvent(SignUpEvents.SignUpButtonPressed)
                    }) {
                        Text("Sign Up")
                    }
                }
            }
        }
    }
}