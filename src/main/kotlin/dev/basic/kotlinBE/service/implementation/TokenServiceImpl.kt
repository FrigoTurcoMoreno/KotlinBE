package dev.basic.kotlinBE.service.implementation

import dev.basic.kotlinBE.service.`interface`.TokenService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*


//implementation of the token service
@Service
class TokenServiceImpl : TokenService {

    //secret recived from the env
    @Value("\${secret}")
    private lateinit var key: String

    //generate token function
    override fun generate(id: UUID): String =
        Jwts.builder()
            .claims()//add the claims if necessary
            .subject(id.toString())//using the id of the user as subject to recover it easily
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 3_600_000))
            .and()
            .signWith(hashKey())
            .compact()

    //verify if the token is still valid
    override fun verifyToken(token: String): Boolean = !isExpired(token)

    //recover the uuid from the token subject
    override fun getUUID(token: String): UUID = UUID.fromString(getClaims(token).subject)

    //support function in order to simplify the verifying token logic
    private fun isExpired(token: String): Boolean = getClaims(token).expiration.before(Date(System.currentTimeMillis()))

    //support function in order to simplify the recovering of the subject
    private fun getClaims(token: String): Claims =
        Jwts.parser()
        .setSigningKey(hashKey())
        .build()
        .parseClaimsJws(token)
        .body

    //support function in order to simplify the hashing logic of the key
    private fun hashKey(): Key = Keys.hmacShaKeyFor(key.toByteArray())
}