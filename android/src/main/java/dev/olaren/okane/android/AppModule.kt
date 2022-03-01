package dev.olaren.okane.android

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.olaren.okane.authentication.repositories.AuthenticationRepository
import dev.olaren.okane.authentication.use_case.AuthenticationUseCases
import dev.olaren.okane.authentication.use_case.SignInWithEmailAndPassword
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthenticationUseCases(): AuthenticationUseCases {
        return AuthenticationUseCases(
            signInWithEmailAndPassword = SignInWithEmailAndPassword(AuthenticationRepository())
        )
    }
}