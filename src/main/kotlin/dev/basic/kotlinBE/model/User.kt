package dev.basic.kotlinBE.model

import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    val id: UUID?,
    @Column(name = "first_name", nullable = false)
    var firstName: String,
    @Column(name = "last_name", nullable = false)
    var lastName: String,
    @Column(unique = true,  nullable = false)
    val email: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    var isAdmin: Boolean
){
    companion object{
        fun toUserRequest(user: User): UserRequest = UserRequest(
            email = user.email,
            password = user.password
        )

        fun toUserResponse(user: User): UserResponse = UserResponse(
            id = user.id!!,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            isAdmin = user.isAdmin
        )
    }

    fun getAuthorities() = if (isAdmin) {
        listOf(SimpleGrantedAuthority("ADMIN"))
    } else {
        listOf(SimpleGrantedAuthority("USER"))
    }
}
