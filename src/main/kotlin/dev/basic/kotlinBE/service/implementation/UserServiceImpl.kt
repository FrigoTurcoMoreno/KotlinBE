package dev.basic.kotlinBE.service.implementation

import dev.basic.kotlinBE.dto.TokenDto
import dev.basic.kotlinBE.dto.UserRequestDto
import dev.basic.kotlinBE.model.User
import dev.basic.kotlinBE.repository.UserRepository
import dev.basic.kotlinBE.service.`interface`.UserService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

//implementation of the user persistence logic
@Service
@Transactional
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository


    override fun findAll(): List<User> = userRepository.findAll()

    //overload of find one user functions
    override fun findUser(email: String): User? = userRepository.findUserByEmail(email)
    override fun findUser(id: UUID): User? = userRepository.findById(id).orElse(null)

    //if there is no user
    override fun insertUser(user: User): User? {
        return if (userRepository.findUserByEmail(user.email) != null) null
        else userRepository.save(user)

    }

    //if there is a user
    override fun updateUser(user: User): User? = user.id?.let {
        userRepository.findById(it).orElse(null)?.let {
            userRepository.save(user)
        }
    }

    override fun deleteUser(id: UUID): Boolean =
        userRepository.findById(id).orElse(null)?.let {
            userRepository.delete(it)
            true
        } ?: false

}