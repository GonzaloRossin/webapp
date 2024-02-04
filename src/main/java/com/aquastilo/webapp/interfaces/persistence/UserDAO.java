package com.aquastilo.webapp.interfaces.persistence;

import com.aquastilo.webapp.model.User;

import java.util.Optional;

public interface UserDAO {

    User createUser(String email, String password);
    Optional<User> getUser(long id);

    Optional<User> getUser(String email);

    User patchUser(User user, String email, String password);
    void deleteUser(User user);
}
