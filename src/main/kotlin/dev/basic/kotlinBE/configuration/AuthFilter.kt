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

//configuration of the authorization filter
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
    )   {
        //recover the authHeader
        val authHeader: String? = request.getHeader("Authorization")

        //if it exists and that is a bearer, im going to take the token present
        authHeader?.takeIf { it.startsWith("Bearer ") }?.substring(7)?.let { token ->
            //verify if the token is still valid
            if (tokenService.verifyToken(token)) {
                //recovering the id of the user
                val userId = tokenService.getUUID(token)
                //if it is found
                userService.findUser(userId)?.let { user ->
                    //set the authorization of the user
                    val authToken = UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
            filterChain.doFilter(request, response)
        } ?: filterChain.doFilter(request, response)

    }

    //not filter the authentication api
    override fun shouldNotFilter(request: HttpServletRequest): Boolean = AntPathMatcher().match("/auth/**", request.servletPath)
}