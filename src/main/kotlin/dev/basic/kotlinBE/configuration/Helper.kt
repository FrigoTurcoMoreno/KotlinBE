package dev.basic.kotlinBE.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class Helper {
    //bean for password encoder
    @Bean
    fun encode(): PasswordEncoder = BCryptPasswordEncoder(11)
}