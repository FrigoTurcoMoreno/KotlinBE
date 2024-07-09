package dev.basic.kotlinBE.model

//model for the login request
data class UserRequest(
    val email: String,
    val password: String
)
