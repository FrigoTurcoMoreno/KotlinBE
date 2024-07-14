package dev.basic.kotlinBE.dto

//model for the login request
data class UserRequestDto(
    val email: String,
    val password: String
)
