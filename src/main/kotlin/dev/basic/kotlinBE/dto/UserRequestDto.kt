package dev.basic.kotlinBE.dto

//model for the login request
data class UserRequest(
    val email: String,
    val password: String
)
