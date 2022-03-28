package app.okaneko.di

import app.okaneko.authentication.di.authenticationSharedModule
import org.kodein.di.DI

val sharedModule = DI.Module("Shared") {
    import(authenticationSharedModule)
}