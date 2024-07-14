package dev.basic.kotlinBE

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
@EnableTransactionManagement
class KotlinBeApplication

fun main(args: Array<String>) {
	runApplication<KotlinBeApplication>(*args)
}
