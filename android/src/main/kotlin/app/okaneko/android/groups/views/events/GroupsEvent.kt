package app.okaneko.android.groups.views.events

sealed class GroupsEvent {
    object PressedLogOutButton : GroupsEvent()
}