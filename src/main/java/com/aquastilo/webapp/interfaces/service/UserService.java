package com.aquastilo.webapp.interfaces.service;

import com.aquastilo.webapp.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    Optional<User> getUser(String email);

    User createUser(String email, String password);

    User patchUser(Long id, String email, String password);

    void deleteUser(Long id);
}
