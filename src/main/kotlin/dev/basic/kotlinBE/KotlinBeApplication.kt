package dev.basic.kotlinBE

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class KotlinBeApplication

fun main(args: Array<String>) {
	runApplication<KotlinBeApplication>(*args)
}
