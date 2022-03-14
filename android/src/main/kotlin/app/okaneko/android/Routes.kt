package app.okaneko.android

sealed class Routes(val route: String) {
    object SignUp : Routes("SignUp")
    object SignIn : Routes("SingIn")
    object Groups : Routes("Groups")
}