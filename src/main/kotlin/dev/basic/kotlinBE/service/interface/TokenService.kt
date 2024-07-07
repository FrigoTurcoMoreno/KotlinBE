package dev.basic.kotlinBE.service.`interface`

import java.util.*

interface TokenService {

    fun generate(id: UUID): String

    fun verifyToken(token: String): Boolean

    fun getUUID(token: String): UUID

}