package dev.olaren.okane.android

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.olaren.okane.android.authentication.views.SignIn
import dev.olaren.okane.android.authentication.views.SignUp
import dev.olaren.okane.android.groups.views.Groups
import dev.olaren.okane.android.ui.theme.OkaneTheme

@Composable
fun ContentView(loggedIn: Boolean) {
    OkaneTheme {
        Surface {
            val navController = rememberNavController()
            val startDestination = if (loggedIn) Routes.Groups.route else Routes.SignIn.route

            NavHost(navController, startDestination) {
                composable(Routes.Groups.route) {
                    Groups(navController)
                }

                composable(Routes.SignUp.route) {
                    SignUp(navController)
                }

                composable(Routes.SignIn.route) {
                    SignIn(navController)
                }
            }
        }
    }
}