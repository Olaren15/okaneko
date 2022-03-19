package app.okaneko.group.di

import app.okaneko.group.repositories.GroupRepository
import app.okaneko.group.repositories.implementation.MongoGroupRepository
import app.okaneko.group.use_cases.CreateGroup
import app.okaneko.group.use_cases.GetGroupById
import app.okaneko.group.use_cases.GetGroupsForUser
import app.okaneko.group.use_cases.GroupsUseCases
import app.okaneko.group.use_cases.implementation.CreateGroupImpl
import app.okaneko.group.use_cases.implementation.GetGroupByIdImpl
import app.okaneko.group.use_cases.implementation.GetGroupsForUserImpl
import org.kodein.di.*

val groupsServerModule = DI.Module("GroupsServerModule") {
    bind {
        singleton {
            GroupsUseCases(
                getGroupsForUser = instance(),
                getGroupById = instance(),
                createGroup = instance(),
            )
        }
    }

    bind<GetGroupById> {
        provider {
            GetGroupByIdImpl(instance())
        }
    }

    bind<CreateGroup> {
        provider {
            CreateGroupImpl(instance())
        }
    }

    bind<GetGroupsForUser> {
        provider {
            GetGroupsForUserImpl(instance())
        }
    }

    bind<GroupRepository> {
        singleton {
            MongoGroupRepository(instance())
        }
    }
}