package com.paulosrlj.straypets.api.controllers;

import com.paulosrlj.straypets.api.dto.LoginResponseDTO;
import com.paulosrlj.straypets.api.dto.auth.AuthenticationDTO;
import com.paulosrlj.straypets.api.dto.auth.RegisterDTO;
import com.paulosrlj.straypets.domain.entities.User;
import com.paulosrlj.straypets.exception.EntityAlreadyExists;
import com.paulosrlj.straypets.repositories.UserRepository;
import com.paulosrlj.straypets.services.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        var user = this.userRepository.findByEmail(data.getEmail());

        if (user != null) {
            throw new EntityAlreadyExists("O usuário já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getEmail(), encryptedPassword, data.getRole());

        this.userRepository.save(newUser);

        return ResponseEntity.badRequest().build();
    }
}
