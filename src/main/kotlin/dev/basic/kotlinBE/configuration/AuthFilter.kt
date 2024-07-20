package dev.basic.kotlinBE.configuration

import dev.basic.kotlinBE.dto.TokenDto
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
class AuthFilter @Autowired constructor(
    private val userService: UserService,
    private val tokenService: TokenService
): OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    )   {
        //recover the authHeader
        val authHeader: String? = request.getHeader("Authorization")

        //if it exists and that is a bearer, im going to take the token present
        authHeader?.takeIf { it.startsWith("Bearer ") }?.substring(7)?.let { token ->
            val tokenDto = TokenDto(token, tokenService.expiresIn(token))
            //verify if the token is still valid
            if (tokenService.verifyToken(tokenDto)) {
                //recovering the id of the user
                val userId = tokenService.getUUID(tokenDto)
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