package dev.basic.kotlinBE.model

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val isAdmin: Boolean
)
