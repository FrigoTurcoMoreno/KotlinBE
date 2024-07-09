package dev.basic.kotlinBE.model

import java.util.UUID

//model for the response
data class UserResponse(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val isAdmin: Boolean
)
