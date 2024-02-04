package com.aquastilo.webapp.service;

import com.aquastilo.webapp.interfaces.persistence.UserDAO;
import com.aquastilo.webapp.interfaces.service.UserService;

import com.aquastilo.webapp.model.User;
import com.aquastilo.webapp.model.enums.status.UserStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private UserDAO userDAO;

    private UserService us;

    @Before
    public void setUp(){
        userDAO = Mockito.mock(UserDAO.class);
        us = new UserServiceImpl(userDAO);
    }

    @Test
    public void testCreateUser(){
        Mockito.when(userDAO.createUser(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new User("pepito@gmail.com", "1234", UserStatus.ACTIVE));

        User user = us.createUser("pepito@gmail.com","1234");

        Assert.assertNotNull(user);
        Assert.assertEquals("pepito@gmail.com", user.getEmail());
        Assert.assertEquals("1234",user.getPassword());
        Assert.assertEquals(UserStatus.ACTIVE, user.getStatus());
    }

    @Test
    public void testCreateUserAlreadyExist(){
        Mockito.when(userDAO.createUser(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new User("pepito@gmail.com", "1234", UserStatus.ACTIVE));

        us.createUser("pepito@gmail.com","1234");

        Mockito.when(userDAO.getUser(Mockito.anyString()))
                .thenReturn(Optional.of( new User("pepito@gmail.com", "1234", UserStatus.ACTIVE)));

        User user = us.createUser("pepito@gmail.com","1234");

        Assert.assertNull(user);
    }

    @Test
    public void testGetUserByEmail(){
        Mockito.when(userDAO.getUser(Mockito.anyString()))
                .thenReturn(Optional.of(new User("pepito@gmail.com", "1234", UserStatus.ACTIVE)));


        Optional<User> userOptional =  us.getUser("pepito@gmail.com");

        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals("pepito@gmail.com", userOptional.get().getEmail());
        Assert.assertEquals("1234", userOptional.get().getPassword());
    }
}
