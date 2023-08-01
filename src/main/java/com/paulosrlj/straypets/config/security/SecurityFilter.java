package com.paulosrlj.straypets.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulosrlj.straypets.exception.EntityNotFoundException;
import com.paulosrlj.straypets.exception.Problem;
import com.paulosrlj.straypets.repositories.UserRepository;
import com.paulosrlj.straypets.services.auth.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Value("${public.endpoints}")
    private List<String> publicEndpoints;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String endpoint = request.getRequestURI();

        if (isPublicEndpoint(endpoint)) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);

        if (token != null) {
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(login);

            if (user == null) {
                Problem problem = Problem.builder()
                        .title("Problema de autenticação")
                        .userMessage("O usuário não existe")
                        .status(400)
                        .detail("O usuário não existe")
                        .build();

                response.setContentType("application/json");
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.getWriter().write(convertObjectToJson(problem));
            }
            else {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }
        }
    }

    public boolean isPublicEndpoint(String endpoint) {
        return publicEndpoints.contains(endpoint);
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
