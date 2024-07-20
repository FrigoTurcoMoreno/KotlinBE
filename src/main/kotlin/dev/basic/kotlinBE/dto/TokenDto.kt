package dev.basic.kotlinBE.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenDto(
    @JsonProperty("jwt")
    val jwt: String,
    @JsonProperty("expires_in")
    val expiresIn: Long
)
