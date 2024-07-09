package dev.basic.kotlinBE.service.`interface`

import dev.basic.kotlinBE.model.User
import java.util.*

//interface to generate the user methods to implement
interface UserService {
    fun findAll(): List<User>

    fun findUser(email: String): User?

    fun findUser(id: UUID): User?

    fun insertUser(user: User): User?

    fun updateUser(user: User): User?

    fun deleteUser(id: UUID): Boolean

}