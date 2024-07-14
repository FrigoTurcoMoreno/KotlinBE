package dev.basic.kotlinBE.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenDto(
    val jwt: String? = null,
    @JsonProperty("expires_in")
    val expiresIn: Long? = null
)
