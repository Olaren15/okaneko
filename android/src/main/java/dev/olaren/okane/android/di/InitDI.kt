package dev.olaren.okane.android.di

import dev.olaren.okane.android.authentication.di.authenticationAndroidModule
import dev.olaren.okane.di.commonModule
import org.kodein.di.DI

fun initDI() = DI.lazy {
    import(commonModule)
    import(authenticationAndroidModule)
}
