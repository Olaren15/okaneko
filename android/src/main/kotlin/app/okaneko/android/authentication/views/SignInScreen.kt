package app.okaneko.android.authentication.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.okaneko.android.Routes
import app.okaneko.android.authentication.viewmodels.SignInViewModel
import app.okaneko.android.authentication.views.components.EmailField
import app.okaneko.android.authentication.views.components.PasswordField
import app.okaneko.android.authentication.views.components.SignInButtons
import app.okaneko.android.authentication.views.events.SignInEvents
import kotlinx.coroutines.flow.collect
import org.kodein.di.compose.rememberViewModel

@Composable
fun SignIn(navController: NavController) {
    val viewModel: SignInViewModel by rememberViewModel()

    val context = LocalContext.current

    var textFieldInError by remember { mutableStateOf(false) }

    val passwordFocusRequester = FocusRequester()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect {
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
                onEmailChange = { viewModel.handleEvent(SignInEvents.EnteredEmail(it)) },
                isError = textFieldInError,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions {
                    passwordFocusRequester.requestFocus()
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                passwordValue = viewModel.password.value,
                onPasswordChange = { viewModel.handleEvent(SignInEvents.EnteredPassword(it)) },
                isError = textFieldInError,
                imeAction = ImeAction.Go,
                keyboardActions = KeyboardActions {
                    viewModel.handleEvent(SignInEvents.PressedEmailSignInButton)
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            SignInButtons(
                onSignInButtonClick = {
                    viewModel.handleEvent(SignInEvents.PressedEmailSignInButton)
                },
                onAnonymousSignInButtonClick = {
                    viewModel.handleEvent(SignInEvents.PressedAnonymousSignInButton)
                },
                onSignUpButtonClick = {
                    navController.navigate(Routes.SignUp.route)
                }
            )
        }
    }
}