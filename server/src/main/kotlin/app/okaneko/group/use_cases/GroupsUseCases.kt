package app.okaneko.group.use_cases

data class GroupsUseCases(
    val getGroupsForUser: GetGroupsForUser,
    val getGroupById: GetGroupById,
    val createGroup: CreateGroup,
)