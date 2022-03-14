package app.okaneko.di

import app.okaneko.authentication.di.authenticationCommonModule
import org.kodein.di.DI

val commonModule = DI.Module(name = "Common") {
    import(authenticationCommonModule)
}