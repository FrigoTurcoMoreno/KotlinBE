package dev.basic.kotlinBE.repository

import dev.basic.kotlinBE.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun findUserByEmail(email: String): User?
}