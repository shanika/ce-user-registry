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

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> findUsersByLastName(String lastName) {
        return userRepository.find(lastName);
    }
}
