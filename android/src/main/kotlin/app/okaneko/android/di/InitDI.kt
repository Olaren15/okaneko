package app.okaneko.android.di

import app.okaneko.android.authentication.di.authenticationAndroidModule
import app.okaneko.di.mobileSharedModule
import org.kodein.di.DI

fun initDI() = DI.lazy {
    import(mobileSharedModule)
    import(authenticationAndroidModule)
}
