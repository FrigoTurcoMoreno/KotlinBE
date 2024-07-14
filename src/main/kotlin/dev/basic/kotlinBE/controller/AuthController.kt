package dev.basic.kotlinBE.controller

import dev.basic.kotlinBE.dto.TokenDto
import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.dto.UserRequestDto
import dev.basic.kotlinBE.service.`interface`.TokenService
import dev.basic.kotlinBE.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//controller for authentication
@RestController
@RequestMapping("auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @PostMapping("/login")
    fun login(@RequestBody userRequestDto: UserRequestDto): ResponseEntity<TokenDto?> =
        userService.findUser(userRequestDto.email)?.let {
           return if (passwordEncoder.matches(userRequestDto.password, it.password)){
               ResponseEntity.ok(tokenService.generate(it.id!!))
           } else ResponseEntity.status(404).body(null)
        } ?: ResponseEntity.status(404).body(null)

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<TokenDto?> {
        user.password = passwordEncoder.encode(user.password)
        user.isAdmin = false
        return userService.insertUser(user)?.let {
            ResponseEntity.ok(tokenService.generate(it.id!!))
        } ?: ResponseEntity.status(409).body(null)
    }
}