package dev.olaren.okane.authentication.use_case

interface IsUserLoggedIn {
    suspend operator fun invoke(): Boolean
}