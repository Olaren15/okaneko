package app.okaneko.android.groups.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.okaneko.android.groups.views.events.GroupsEvent
import app.okaneko.authentication.use_case.AuthenticationUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class GroupsViewModel(private val authenticationUseCases: AuthenticationUseCases) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun handleEvent(event: GroupsEvent) {
        when (event) {
            is GroupsEvent.PressedLogOutButton -> {
                viewModelScope.launch {
                    authenticationUseCases.signOut()
                    _eventFlow.emit(UiEvent.SignedOut)
                }
            }
        }
    }

    sealed class UiEvent {
        object SignedOut : UiEvent()
    }
}