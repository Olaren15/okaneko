package app.okaneko.group.use_case

data class GroupsUseCases(
    val getGroupsForUser: GetGroupsForUser,
    val getGroupById: GetGroupById,
    val createGroup: CreateGroup,
)