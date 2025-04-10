package com.example.userService.service;

import com.example.userService.entity.User;
import com.example.userService.entity.UserPreference;
import com.example.userService.entity.Role;
import com.example.userService.repository.UserRepository;
import com.example.userService.repository.UserPreferenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserPreferenceRepository userPreferenceRepository;

    public UserServiceImpl(UserRepository userRepository, UserPreferenceRepository userPreferenceRepository) {
        this.userRepository = userRepository;
        this.userPreferenceRepository = userPreferenceRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("Fetched users: " + users); // Debugging log
        return users;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setAddress(updatedUser.getAddress());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setRole(updatedUser.getRole()); // Update role if needed
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserPreference> getUserPreferences(Long userId) {
        return userPreferenceRepository.findByUserId(userId);
    }

    @Override
    public UserPreference setUserPreferences(Long userId, UserPreference preferredCuisine) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        preferredCuisine.setUser(user);
        return userPreferenceRepository.save(preferredCuisine);
    }

    @Override
    public User updateUserRole(Long userId, Role role) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setRole(role);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public UserPreference updateUserPreferences(Long userId, UserPreference preferredCuisine) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userPreferenceRepository.findByUserId(userId)
                .stream()
                .findFirst()
                .map(existingPreference -> {
                    existingPreference.setPreferredCuisine(preferredCuisine.getPreferredCuisine());
                    return userPreferenceRepository.save(existingPreference);
                })
                .orElseGet(() -> setUserPreferences(userId, preferredCuisine));
    }
}