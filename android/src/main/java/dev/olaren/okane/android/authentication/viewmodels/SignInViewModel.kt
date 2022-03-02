package dev.olaren.okane.android.authentication.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.olaren.okane.android.authentication.views.events.SignInEvents
import dev.olaren.okane.authentication.exceptions.InvalidCredentials
import dev.olaren.okane.authentication.exceptions.SignInError
import dev.olaren.okane.authentication.use_case.AuthenticationUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authenticationUseCases: AuthenticationUseCases) :
    ViewModel() {

    private val _email = mutableStateOf(TextFieldValue(""))
    val email: State<TextFieldValue> = _email

    private val _password = mutableStateOf(TextFieldValue(""))
    val password: State<TextFieldValue> = _password

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SignInEvents) {
        when (event) {
            is SignInEvents.EnteredEmail -> {
                _email.value = event.value
            }

            is SignInEvents.EnteredPassword -> {
                _password.value = event.value
            }

            is SignInEvents.PressedEmailSignInButton -> {
                viewModelScope.launch {
                    authenticationUseCases.signInWithEmailAndPassword(
                        _email.value.text,
                        _password.value.text,
                    ).onSuccess {
                        _eventFlow.emit(UiEvent.SignedIn)

                    }.onFailure {
                        when (it) {
                            is InvalidCredentials -> {
                                _eventFlow.emit(UiEvent.InvalidCredentials)
                            }

                            is SignInError -> {
                                _eventFlow.emit(UiEvent.SignInError)
                            }
                        }
                    }
                }
            }

            is SignInEvents.PressedAnonymousSignInButton -> {
                viewModelScope.launch {
                    authenticationUseCases.signInAnonymously()
                        .onSuccess {
                            _eventFlow.emit(UiEvent.SignedIn)
                        }.onFailure {
                            _eventFlow.emit(UiEvent.SignInError)
                        }
                }
            }
        }
    }

    sealed class UiEvent {
        object SignedIn : UiEvent()
        object SignInError : UiEvent()
        object InvalidCredentials : UiEvent()
    }
}