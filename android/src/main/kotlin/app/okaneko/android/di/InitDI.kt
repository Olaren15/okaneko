package app.okaneko.android.di

import app.okaneko.android.authentication.di.authenticationAndroidModule
import app.okaneko.di.commonModule
import org.kodein.di.DI

fun initDI() = DI.lazy {
    import(commonModule)
    import(authenticationAndroidModule)
}
