package dev.olaren.okane.android.groups.views

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.olaren.okane.android.Routes
import dev.olaren.okane.android.groups.viewmodels.GroupsViewModel
import dev.olaren.okane.android.groups.views.events.GroupsEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Groups(navController: NavController, viewModel: GroupsViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is GroupsViewModel.UiEvent.SignedOut -> {
                    navController.navigate(Routes.SignIn.route) {
                        popUpTo(Routes.Groups.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Button(onClick = {
        viewModel.onEvent(GroupsEvent.PressedLogOutButton)
    }) {
        Text("Sign Out")
    }
}