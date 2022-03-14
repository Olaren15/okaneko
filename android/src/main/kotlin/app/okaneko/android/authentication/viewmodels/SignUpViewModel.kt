package app.okaneko.android.authentication.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.okaneko.android.authentication.views.events.SignUpEvents
import app.okaneko.authentication.errors.SignUpError
import app.okaneko.authentication.use_case.AuthenticationUseCases
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val authenticationUseCases: AuthenticationUseCases) : ViewModel() {
    private val _email = mutableStateOf(TextFieldValue(""))
    val email: State<TextFieldValue> = _email

    private val _password = mutableStateOf(TextFieldValue(""))
    val password: State<TextFieldValue> = _password

    private val _confirmedPassword = mutableStateOf(TextFieldValue(""))
    val confirmedPassword: State<TextFieldValue> = _confirmedPassword

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun handleEvent(event: SignUpEvents) {
        when (event) {
            is SignUpEvents.EnteredEmail -> {
                _email.value = event.value
            }

            is SignUpEvents.EnteredPassword -> {
                _password.value = event.value
            }

            is SignUpEvents.EnteredConfirmedPassword -> {
                _confirmedPassword.value = event.value
            }

            is SignUpEvents.SignUpButtonPressed -> {
                viewModelScope.launch {
                    authenticationUseCases.signUpWithEmailAndPassword(
                        email.value.text,
                        password.value.text,
                        confirmedPassword.value.text
                    ).onSuccess {
                        _eventFlow.emit(UiEvent.SignedUp)
                    }.onFailure {
                        when (it) {
                            is SignUpError.InvalidEmailError -> {
                                _eventFlow.emit(UiEvent.InvalidEmailEntered)
                            }

                            is SignUpError.PasswordsNotMatchingError -> {
                                _eventFlow.emit(UiEvent.PasswordDidNotMatch)
                            }

                            is SignUpError.PasswordTooWeakError -> {
                                _eventFlow.emit(UiEvent.PasswordIsTooWeak)
                            }

                            is SignUpError.UserAlreadyExistsError -> {
                                _eventFlow.emit(UiEvent.UserAlreadyExists)
                            }
                            SignUpError.UnknownError -> {
                                _eventFlow.emit(UiEvent.SignUpError)
                            }
                        }
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        object SignedUp : UiEvent()
        object InvalidEmailEntered : UiEvent()
        object PasswordDidNotMatch : UiEvent()
        object PasswordIsTooWeak : UiEvent()
        object UserAlreadyExists : UiEvent()
        object SignUpError : UiEvent()
    }
}