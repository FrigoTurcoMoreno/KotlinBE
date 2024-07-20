package dev.basic.kotlinBE.service.`interface`

import dev.basic.kotlinBE.dto.TokenDto
import dev.basic.kotlinBE.dto.UserLoginDto
import dev.basic.kotlinBE.dto.UserResponseDto
import dev.basic.kotlinBE.dto.UserUpdateDto
import dev.basic.kotlinBE.model.User
import java.util.*

//interface to generate the user methods to implement
interface UserService {
    fun findAll(): List<User>

    fun findUser(email: String): User?

    fun findUser(id: UUID): User?

    fun insertUser(user: User): UserResponseDto?

    fun updateUser(userUpdateDto: UserUpdateDto): UserResponseDto

    fun deleteUser(id: UUID): Boolean

    fun login(userLogin: UserLoginDto): TokenDto?

    fun register(user: User): TokenDto?
}