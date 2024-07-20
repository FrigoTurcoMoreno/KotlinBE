package dev.basic.kotlinBE.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

//model for the response
data class UserResponseDto(
    @JsonProperty("id")
    val id: UUID,
    @JsonProperty("first_name")
    val firstName: String,
    @JsonProperty("last_name")
    val lastName: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("is_admin")
    val isAdmin: Boolean
)
