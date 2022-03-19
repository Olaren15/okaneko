package app.okaneko.group.di

import app.okaneko.group.repository.GroupRepository
import app.okaneko.group.repository.implementation.MongoGroupRepository
import app.okaneko.group.use_case.CreateGroup
import app.okaneko.group.use_case.GetGroupById
import app.okaneko.group.use_case.GetGroupsForUser
import app.okaneko.group.use_case.GroupsUseCases
import app.okaneko.group.use_case.implementation.CreateGroupImpl
import app.okaneko.group.use_case.implementation.GetGroupByIdImpl
import app.okaneko.group.use_case.implementation.GetGroupsForUserImpl
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