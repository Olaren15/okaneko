package app.okaneko.base.data.dto

import kotlinx.datetime.Instant

interface Dto {
    val createdAt: Instant
    val updatedAt: Instant
}