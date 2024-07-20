package dev.basic.kotlinBE.dto

import com.fasterxml.jackson.annotation.JsonProperty

//model for the login request
data class UserLoginDto(
    @JsonProperty("email", required = true)
    val email: String,
    @JsonProperty("password", required = true)
    val password: String
)
