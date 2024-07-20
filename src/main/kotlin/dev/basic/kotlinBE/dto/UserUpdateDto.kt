package dev.basic.kotlinBE.dto

import com.fasterxml.jackson.annotation.JsonProperty
import dev.basic.kotlinBE.model.User
import java.util.*

data class UserUpdateDto(
    @JsonProperty("id")
    val id: UUID,
    @JsonProperty("first_name", required = false)
    val firstName: String?,
    @JsonProperty("last_name", required = false)
    val lastName: String?,
    @JsonProperty("email", required = false)
    val email: String?,
    @JsonProperty("password", required = false)
    val password: String?,
    @JsonProperty("is_admin", required = false)
    val isAdmin: Boolean?
) {
    companion object {
        fun userUpdateDtoToUser(userUpdateDto: UserUpdateDto, existingUser: User): User {
            return User(
                id = userUpdateDto.id,
                firstName = userUpdateDto.firstName ?: existingUser.firstName,
                lastName = userUpdateDto.lastName ?: existingUser.lastName,
                email = userUpdateDto.email ?: existingUser.email,
                password = userUpdateDto.password?.let { existingUser.password } ?: existingUser.password,
                isAdmin = userUpdateDto.isAdmin ?: existingUser.isAdmin
            )
        }

        fun userUpdateDtoToUserResponseDto(user: User): UserResponseDto {
            return UserResponseDto(
                id = user.id!!,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                isAdmin = user.isAdmin
            )
        }
    }


}
