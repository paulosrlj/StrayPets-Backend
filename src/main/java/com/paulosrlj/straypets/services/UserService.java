package com.paulosrlj.straypets.services;

import com.paulosrlj.straypets.domain.entities.User;
import com.paulosrlj.straypets.exception.EntityNotFoundException;
import com.paulosrlj.straypets.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long userId) {
        try {
            var user = this.userRepository.findById(userId);
            if (user.isEmpty()) {
                throw new EntityNotFoundException("O usuário não existe.");
            }

            return user.get();
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(ex.getMessage());
        }
    }

    @Transactional
    public void deactivateUser(Long userId) {
        var user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("O usuário não existe.");
        }

        user.get().deactivateUser();
    }

    @Transactional
    public void activateUser(Long userId) {
        var user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("O usuário não existe.");
        }

        user.get().activateUser();
    }
}
