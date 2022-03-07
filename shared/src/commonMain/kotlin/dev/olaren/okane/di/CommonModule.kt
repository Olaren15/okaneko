package dev.olaren.okane.di

import dev.olaren.okane.authentication.di.authenticationCommonModule
import org.kodein.di.DI

val commonModule = DI.Module(name = "Common") {
    import(authenticationCommonModule)
}