package dev.basic.kotlinBE.service.implementation

import dev.basic.kotlinBE.service.`interface`.TokenService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class TokenServiceImpl : TokenService {

    @Value("\${secret}")
    private lateinit var key: String

    override fun generate(id: UUID): String =
        Jwts.builder()
            .claims()
            .subject(id.toString())
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 3_600_000))
            .and()
            .signWith(hashKey())
            .compact()

    override fun verifyToken(token: String): Boolean = !isExpired(token)

    override fun getUUID(token: String): UUID = UUID.fromString(getClaims(token).subject)


    private fun isExpired(token: String): Boolean = getClaims(token).expiration.before(Date(System.currentTimeMillis()))

    private fun getClaims(token: String): Claims =
        Jwts.parser()
        .setSigningKey(hashKey())
        .build()
        .parseClaimsJws(token)
        .body

    private fun hashKey(): Key = Keys.hmacShaKeyFor(key.toByteArray())
}