package dev.olaren.okane.group.di

import dev.olaren.okane.group.use_cases.GetGroupById
import dev.olaren.okane.group.use_cases.GroupsUseCases
import dev.olaren.okane.group.use_cases.implementation.GetGroupByIdImpl
import org.kodein.di.*

val groupsServerModule = DI.Module("GroupsServerModule") {
    bind {
        singleton {
            GroupsUseCases(
                getGroupById = instance()
            )
        }
    }

    bind<GetGroupById> {
        provider {
            GetGroupByIdImpl()
        }
    }
}