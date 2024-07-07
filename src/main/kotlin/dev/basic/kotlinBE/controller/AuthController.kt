package dev.basic.kotlinBE.controller

import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.model.UserRequest
import dev.basic.kotlinBE.service.`interface`.TokenService
import dev.basic.kotlinBE.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun login(@RequestBody userRequest: UserRequest): ResponseEntity<String> =
        userService.findUser(userRequest.email)?.let {
           return if (passwordEncoder.matches(userRequest.password, it.password)){
               ResponseEntity.ok(tokenService.generate(it.id!!))
           } else ResponseEntity.status(404).body("")
        } ?: ResponseEntity.status(404).body("")

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        user.password = passwordEncoder.encode(user.password)
        user.isAdmin = false
        return userService.insertUser(user)?.let {
            ResponseEntity.ok(tokenService.generate(it.id!!))
        } ?: ResponseEntity.status(409).body("")
    }
}