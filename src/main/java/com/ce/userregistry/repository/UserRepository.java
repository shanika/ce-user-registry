package com.ce.userregistry.repository;

import com.ce.userregistry.entity.User;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Executable
    List<User> find(String lastName);
}
