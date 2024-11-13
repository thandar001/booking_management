package com.bk.system.security;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	        if (request.getServletPath().contains("/api/auth")) {
	            filterChain.doFilter(request, response);
	            return;
	        }

	        final String authorizationHeader = request.getHeader("Authorization");
	        final String jwtToken;
	        final String username;

	        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
	            filterChain.doFilter(request, response);
	            return;
	        }

	        jwtToken = authorizationHeader.substring(7);
	        username = jwtService.extractUsername(jwtToken);

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            if (jwtService.validateToken(jwtToken, userDetails)) {
	                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        }
	        filterChain.doFilter(request, response);
	    }

}
