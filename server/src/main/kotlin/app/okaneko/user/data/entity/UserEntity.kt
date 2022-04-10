package app.okaneko.user.data.entity

import app.okaneko.database.data.entity.Entity
import app.okaneko.user.data.dto.User
import app.okaneko.user.data.dto.UserDetails
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserEntity(
    @Contextual
    @SerialName("_id")
    override var id: UUID?,
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val loginOptions: LoginOptions,
    override val createdAt: Instant?,
    override var updatedAt: Instant?
) : Entity<User> {
    fun isAnonymous(): Boolean {
        return loginOptions.hashedPassword == null
    }

    override fun toDto(): User {
        return User(
            id = id!!.toString(),
            details = UserDetails(
                name = name,
                email = email,
                photoUrl = photoUrl,
            ),
            isAnonymous = isAnonymous(),
            createdAt = createdAt ?: Clock.System.now(),
            updatedAt = updatedAt ?: Clock.System.now()
        )
    }
}
