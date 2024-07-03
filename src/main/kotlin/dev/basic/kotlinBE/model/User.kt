package dev.basic.kotlinBE.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    val id: UUID?,
    @Column(name = "first_name", nullable = false)
    val firstName: String,
    @Column(name = "last_name", nullable = false)
    val lastName: String,
    @Column(unique = true,  nullable = false)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val role: Role
){
    companion object{
        fun toUserRequest(user: User): UserRequest = UserRequest(
            email = user.email,
            password = user.password
        )

        fun toUserResponse(user: User): UserResponse = UserResponse(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            role = user.role
        )
    }
}

enum class Role{
    USER,
    ADMIN
}
