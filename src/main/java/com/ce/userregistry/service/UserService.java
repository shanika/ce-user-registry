package com.ce.userregistry.service;

import com.ce.userregistry.entity.User;
import com.ce.userregistry.repository.UserRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Singleton
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Creates new user
     * @param user User entity object
     */
    public void addUser(User user) {
        userRepository.save(user);
    }

    /**
     * Filter users by surname.
     * @param surname search key for surname
     * @return A list of users filtered by surname
     */
    public List<User> findUsersBySurname(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException("Surname search value can not be null");
        }
        return userRepository.find(surname);
    }
}
