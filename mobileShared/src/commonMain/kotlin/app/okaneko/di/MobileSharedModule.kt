package app.okaneko.di

import app.okaneko.authentication.di.authenticationCommonModule
import org.kodein.di.DI

val mobileSharedModule = DI.Module(name = "Common") {
    import(authenticationCommonModule)
    import(sharedModule)
}