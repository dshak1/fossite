package com.fossite.homework.service;

import com.fossite.homework.model.User;
import com.fossite.homework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(String username) {
        return userRepository.findByUsername(username)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(username);
                    // Default avatar
                    newUser.setAvatarAnimal("BEAR");
                    newUser.setAvatarColor("BLUE");
                    return userRepository.save(newUser);
                });
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateUserAvatar(Long userId, String animal, String color) {
        User user = getUser(userId);
        user.setAvatarAnimal(animal);
        user.setAvatarColor(color);
        userRepository.save(user);
    }
}

