package app.okaneko.group.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: String,
    val usersId: List<String>,
    val name: String,
)