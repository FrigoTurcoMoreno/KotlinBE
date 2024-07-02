package dev.basic.kotlinBE.model

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import lombok.ToString
import java.util.UUID

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
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
)

enum class Role{
    USER,
    ADMIN
}
