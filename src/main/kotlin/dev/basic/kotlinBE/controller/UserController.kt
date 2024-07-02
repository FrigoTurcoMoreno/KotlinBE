package dev.basic.kotlinBE.controller

import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("api/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun findAll(): List<User> = userService.findAll()

    @GetMapping("/{email}")
    fun findUser(@PathVariable("email") email: String): User? = userService.findUser(email)

    @PostMapping
    fun insertUser(@RequestBody user: User): User? = userService.insertUser(user)

    @PutMapping
    fun updateUser(@RequestBody user: User): User? = userService.updateUser(user)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: UUID): Boolean = userService.deleteUser(id)
}