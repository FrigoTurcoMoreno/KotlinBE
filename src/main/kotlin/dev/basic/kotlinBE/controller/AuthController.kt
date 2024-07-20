package dev.basic.kotlinBE.controller

import dev.basic.kotlinBE.dto.TokenDto
import dev.basic.kotlinBE.dto.UserLoginDto
import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//controller for authentication
@RestController
@RequestMapping("auth")
class AuthController @Autowired constructor(
    private val userService: UserService
){

    @PostMapping("/login")
    fun login(@RequestBody userRequestDto: UserLoginDto): ResponseEntity<TokenDto?> =
        userService.login(userRequestDto)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(404).body(null)


    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<TokenDto?> =
        userService.register(user)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(409).body(null)


}