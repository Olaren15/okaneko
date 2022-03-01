package dev.olaren.okane.android

sealed class Routes(val route: String) {
    object SignUp : Routes("SignUp")
    object SignIn : Routes("SingIn")
    object Groups : Routes("Groups")
}