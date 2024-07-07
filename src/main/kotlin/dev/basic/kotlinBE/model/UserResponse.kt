package dev.basic.kotlinBE.model

data class UserResponse(
    val firstName: String,
    val lastName: String,
    val email: String,
    val isAdmin: Boolean
)
