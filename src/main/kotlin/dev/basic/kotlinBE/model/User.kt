package dev.basic.kotlinBE.model

import com.fasterxml.jackson.annotation.JsonProperty
import dev.basic.kotlinBE.dto.UserResponseDto
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
    @JsonProperty("first_name")
    var firstName: String = "",
    @Column(name = "last_name", nullable = false)
    @JsonProperty("last_name")
    var lastName: String = "",
    @Column(unique = true,  nullable = false)
    @JsonProperty("email")
    var email: String = "",
    @Column(nullable = false)
    @JsonProperty("password")
    var password: String = "",
    @Column(nullable = false)
    @JsonProperty("is_admin")
    var isAdmin: Boolean = false
){
    companion object{
        //static function to map the user into the UserResponse model
        fun toUserResponseDto(user: User): UserResponseDto = UserResponseDto(
            id = user.id!!,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            isAdmin = user.isAdmin
        )
    }

    //function to set the authorities depending on the isAdmin variable
    fun getAuthorities() = if (isAdmin) {
        listOf(SimpleGrantedAuthority("ADMIN"))
    } else {
        listOf(SimpleGrantedAuthority("USER"))
    }
}
