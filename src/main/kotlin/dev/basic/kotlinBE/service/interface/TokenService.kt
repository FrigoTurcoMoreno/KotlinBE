package dev.basic.kotlinBE.service.`interface`

import dev.basic.kotlinBE.dto.TokenDto
import java.util.*

//interface to generate the token methods to implement
interface TokenService {

    fun generate(id: UUID): TokenDto

    fun verifyToken(token: TokenDto): Boolean

    fun getUUID(token: TokenDto): UUID

    fun expiresIn(token: String): Long
}