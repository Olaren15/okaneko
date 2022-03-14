package app.okaneko.group.di

import app.okaneko.group.use_cases.GetGroupById
import app.okaneko.group.use_cases.GroupsUseCases
import app.okaneko.group.use_cases.implementation.GetGroupByIdImpl
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