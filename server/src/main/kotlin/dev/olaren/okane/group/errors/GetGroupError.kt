package dev.olaren.okane.group.errors

sealed class GetGroupError {
    object IdNotFound : GetGroupError()
    object NotPermittedError : GetGroupError()
}