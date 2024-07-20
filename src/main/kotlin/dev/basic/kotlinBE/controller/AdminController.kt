package dev.basic.kotlinBE.controller

import dev.basic.kotlinBE.dto.UserResponseDto
import dev.basic.kotlinBE.dto.UserUpdateDto
import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*

//api reachable only from admin
@RestController
@RequestMapping("api/admin")
class AdminController @Autowired constructor(
    private val userService: UserService

){


    @GetMapping
    fun findAll(): ResponseEntity<List<UserResponseDto>> = ResponseEntity.ok(userService.findAll().map {
        User.toUserResponseDto(it)
    })

    @GetMapping("/find")
    fun findUser(@RequestParam("email") email: String): ResponseEntity<User?> = userService.findUser(email)?.let {
        ResponseEntity.ok(it)
    } ?: ResponseEntity.status(404).body(null)

    @PostMapping
    fun insertUser(@RequestBody user: User): ResponseEntity<UserResponseDto?>  {
        userService.insertUser(user)?.let {
            return ResponseEntity.ok(it)
        } ?: return ResponseEntity.status(409).body(null)
    }

    @PutMapping
    fun updateUser(@RequestBody userUpdateDto: UserUpdateDto): ResponseEntity<UserResponseDto?> = ResponseEntity.ok(userService.updateUser(userUpdateDto))

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: UUID): ResponseEntity<Boolean> = ResponseEntity.ok(userService.deleteUser(id))
}