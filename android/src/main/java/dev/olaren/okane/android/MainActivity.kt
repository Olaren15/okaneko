package dev.olaren.okane.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.olaren.okane.android.authentication.views.SignIn
import dev.olaren.okane.android.authentication.views.SignUp
import dev.olaren.okane.android.groups.views.Groups
import dev.olaren.okane.android.ui.theme.OkaneTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OkaneTheme {
                val navController = rememberNavController()

                val loggedIn = false
                val startDestination = if (loggedIn) Routes.Groups.route else Routes.SignIn.route

                NavHost(navController, startDestination) {
                    composable(Routes.Groups.route) {
                        Groups()
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
}
