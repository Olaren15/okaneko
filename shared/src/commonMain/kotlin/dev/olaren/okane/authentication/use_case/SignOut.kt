package dev.olaren.okane.authentication.use_case

interface SignOut {
    suspend operator fun invoke()
}