package app.okaneko.di

import app.okaneko.user.di.authenticationSharedModule
import org.kodein.di.DI

val sharedModule = DI.Module("Shared") {
    import(authenticationSharedModule)
}