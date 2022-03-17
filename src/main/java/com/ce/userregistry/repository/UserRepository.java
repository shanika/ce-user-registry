package com.ce.userregistry.repository;

import com.ce.userregistry.entity.User;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class UserRepository {
    public void save(User user) {

    }

    public List<User> findByLastName(String lastName) {
        return null;
    }
}
