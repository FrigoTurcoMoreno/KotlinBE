package dev.basic.kotlinBE.service.implementation

import dev.basic.kotlinBE.dto.TokenDto
import dev.basic.kotlinBE.dto.UserLoginDto
import dev.basic.kotlinBE.dto.UserResponseDto
import dev.basic.kotlinBE.dto.UserUpdateDto
import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.repository.UserRepository
import dev.basic.kotlinBE.service.`interface`.TokenService
import dev.basic.kotlinBE.service.`interface`.UserService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

//implementation of the user persistence logic
@Service
@Transactional
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService
)  : UserService{

    override fun findAll(): List<User> = userRepository.findAll()

    //overload of find one user functions
    override fun findUser(email: String): User? = userRepository.findUserByEmail(email)
    override fun findUser(id: UUID): User? = userRepository.findById(id).orElse(null)

    //if there is no user
    override fun insertUser(user: User): UserResponseDto? {
        return if (userRepository.findUserByEmail(user.email) != null) null
        else {
            user.password = passwordEncoder.encode(user.password)
            User.toUserResponseDto(userRepository.save(user))
        }

    }

    //if there is a user
    override fun updateUser(userUpdateDto: UserUpdateDto): UserResponseDto =
        userUpdateDto.id.let { userId ->
            val user = findUser(userId)!!
            val updatedUser = UserUpdateDto.userUpdateDtoToUser(userUpdateDto, user)

            userUpdateDto.password?.let { password ->
                updatedUser.password = passwordEncoder.encode(password)
            }

            val savedUser = userRepository.save(updatedUser)
            UserUpdateDto.userUpdateDtoToUserResponseDto(savedUser)
        }

    override fun deleteUser(id: UUID): Boolean =
        userRepository.findById(id).orElse(null)?.let {
            userRepository.delete(it)
            true
        } ?: false

    override fun login(userLogin: UserLoginDto): TokenDto? =
            findUser(userLogin.email)?.let {
                return if (passwordEncoder.matches(userLogin.password, it.password)) {
                    tokenService.generate(it.id!!)
                } else null
            }


    override fun register(user: User): TokenDto? {
        user.password = passwordEncoder.encode(user.password)
        user.isAdmin = false
        return insertUser(user)?.let {
            tokenService.generate(it.id!!)
        }
    }

}