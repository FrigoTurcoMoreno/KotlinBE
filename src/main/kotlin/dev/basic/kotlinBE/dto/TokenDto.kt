package dev.basic.kotlinBE.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenDto(
    val jwt: String,
    @JsonProperty("expires_in")
    val expiresIn: Long
)
