package dev.olaren.okane.authentication.use_case

data class AuthenticationUseCases(
    val signUpWithEmailAndPassword: SignUpWithEmailAndPassword,
    val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    val signInAnonymously: SignInAnonymously,
    val signOut: SignOut,
    val isUserLoggedIn: IsUserLoggedIn,
)