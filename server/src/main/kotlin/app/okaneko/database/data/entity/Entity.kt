package app.okaneko.database.data.entity

import app.okaneko.base.data.dto.Dto
import kotlinx.datetime.Instant
import java.util.*

interface Entity<T : Dto> {
    var id: UUID?

    val createdAt: Instant?
    var updatedAt: Instant?

    fun toDto(): T
}