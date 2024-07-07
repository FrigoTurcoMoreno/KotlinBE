package dev.basic.kotlinBE.configuration

import dev.basic.kotlinBE.service.`interface`.TokenService
import dev.basic.kotlinBE.service.`interface`.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter


@Component
class AuthFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var tokenService: TokenService


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
       val authHeader: String? = request.getHeader("Authorization")

        authHeader?.takeIf { it.startsWith("Bearer ") }?.substring(7)?.let { token ->
            if (tokenService.verifyToken(token)) {
                val userId = tokenService.getUUID(token)
                userService.findUser(userId)?.let { user ->
                    val authToken = UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
            filterChain.doFilter(request, response)
        } ?: filterChain.doFilter(request, response)

    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean = AntPathMatcher().match("/auth/**", request.servletPath)
}