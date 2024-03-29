package com.paulosrlj.straypets.api.controllers;

import com.paulosrlj.straypets.api.dto.LoginResponseDTO;
import com.paulosrlj.straypets.api.dto.auth.AuthenticationDTO;
import com.paulosrlj.straypets.api.dto.auth.RegisterDTO;
import com.paulosrlj.straypets.api.dto.output.OutputUser;
import com.paulosrlj.straypets.config.modelMapper.UserDtoConverter;
import com.paulosrlj.straypets.domain.entities.User;
import com.paulosrlj.straypets.exception.EntityAlreadyExists;
import com.paulosrlj.straypets.repositories.UserRepository;
import com.paulosrlj.straypets.services.UserService;
import com.paulosrlj.straypets.services.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDtoConverter userDtoConverter;

    @GetMapping("{id}")
    public OutputUser findUser(@PathVariable("id") Long userId) {
        var user = this.userService.getUser(userId);

        return userDtoConverter.convertToOutput(user);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid AuthenticationDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

            var auth = this.authenticationManager.authenticate(usernamePassword);

            if (auth != null && auth.getPrincipal() != null) {
                var token = tokenService.generateToken((User) auth.getPrincipal());
                return new LoginResponseDTO(token);
            } else {
                throw new com.paulosrlj.straypets.exception.AuthenticationException("O usuário não existe ou credenciais incorretas");
            }
        } catch (AuthenticationException ex) {
            throw new com.paulosrlj.straypets.exception.AuthenticationException("O usuário não existe, ou credenciais incorretas");
        }
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

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/deactivate-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deactivateAccount(@PathVariable("id") Long userId){
        this.userService.deactivateUser(userId);
    }

    @PutMapping("/activate-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void activateAccount(@PathVariable("id") Long userId){
        this.userService.activateUser(userId);
    }
}
