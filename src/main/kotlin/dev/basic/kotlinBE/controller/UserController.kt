package dev.basic.kotlinBE.controller

import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.model.UserResponse
import dev.basic.kotlinBE.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun findAll(): ResponseEntity<List<UserResponse>> = ResponseEntity.ok(userService.findAll().map {
        User.toUserResponse(it)
    })

    @GetMapping("/{email}")
    fun findUser(@PathVariable("email") email: String): ResponseEntity<User?> = userService.findUser(email)?.let {
        ResponseEntity.ok(it)
    } ?: ResponseEntity.status(404).body(null)

    @PostMapping
    fun insertUser(@RequestBody user: User): ResponseEntity<UserResponse?> = userService.insertUser(user)?.let {
        ResponseEntity.ok(User.toUserResponse(it))
    } ?: ResponseEntity.status(409).body(null)

    @PutMapping
    fun updateUser(@RequestBody user: User): ResponseEntity<UserResponse?> = userService.updateUser(user)?.let {
        ResponseEntity.ok(User.toUserResponse(it))
    } ?: ResponseEntity.status(404).body(null)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: UUID): ResponseEntity<Boolean> = ResponseEntity.ok(userService.deleteUser(id))
}