package app.okaneko.group.errors

sealed class GetGroupError {
    object IdNotFound : GetGroupError()
    object NotPermittedError : GetGroupError()
}