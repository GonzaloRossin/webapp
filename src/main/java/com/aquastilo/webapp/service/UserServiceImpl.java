package com.aquastilo.webapp.service;

import com.aquastilo.webapp.interfaces.persistence.UserDAO;
import com.aquastilo.webapp.interfaces.service.UserService;
import com.aquastilo.webapp.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Override
    public Optional<User> getUser(String email) {
        return userDAO.getUser(email);
    }

    @Transactional
    @Override
    public User createUser(String email, String password) {
        Optional<User> userOptional = userDAO.getUser(email);
        if(userOptional.isPresent()){
            return  null;
        }
        return userDAO.createUser(email, password);
    }

    @Transactional
    @Override
    public User patchUser(Long id, String email, String password) {
        Optional<User> userOptional = getUser(id);
        return userOptional.map(user -> userDAO.patchUser(user, email, password)).orElse(null);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        Optional<User> userOptional = userDAO.getUser(id);
        userOptional.ifPresent(userDAO::deleteUser);
    }
}
