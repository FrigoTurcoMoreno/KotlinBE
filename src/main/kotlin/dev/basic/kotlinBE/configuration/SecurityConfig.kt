package dev.basic.kotlinBE.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

//basic be security configuration
@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var authFilter: AuthFilter

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                //permit all the requests to authentication api
                it.requestMatchers("/auth/**").permitAll()
                    //permit all the requests to admin api only for the admin
                    .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                    //permit all the other requests (kind of the user appi)
                    .anyRequest().hasAnyAuthority("USER", "ADMIN")
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            //adding the authorization filter
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

    //bean for password encoder
    @Bean
    fun encode(): PasswordEncoder = BCryptPasswordEncoder(11)
}