package dev.olaren.okane.authentication.use_case

data class AuthenticationUseCases(
    val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    val signInAnonymously: SignInAnonymously,
    val signOut: SignOut,
)