package dev.basic.kotlinBE.controller

import dev.basic.kotlinBE.dto.TokenDto
import dev.basic.kotlinBE.dto.UserResponseDto
import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.service.`interface`.TokenService
import dev.basic.kotlinBE.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//controller for admin and user to get information
@RestController
@RequestMapping("api/user")
class UserController @Autowired constructor(
    private val userService: UserService,
    private val tokenService: TokenService
) {




    @GetMapping
    fun getInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) authHeader: String?): ResponseEntity<UserResponseDto?> =
        authHeader?.takeIf { it.startsWith("Bearer ") }?.substring(7)?.let { token ->
            val tokenDto = TokenDto(token, tokenService.expiresIn(token))
            userService.findUser(tokenService.getUUID(tokenDto))?.let { user ->
                ResponseEntity.ok(User.toUserResponseDto(user))
            } ?: ResponseEntity.status(403).body(null)
        } ?: ResponseEntity.status(403).body(null)

}