package com.aquastilo.webapp.persistence;

import com.aquastilo.webapp.interfaces.persistence.UserDAO;
import com.aquastilo.webapp.model.User;
import com.aquastilo.webapp.model.enums.status.UserStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.aquastilo.webapp.persistence")
@DataJpaTest
public class UserDaoTest {


    @Autowired
    private UserDAO userDAO;
    @Autowired
    private EntityManager em;


    private void setUpData(){
        User user1 = new User("example@gmail.com", "1234", UserStatus.ACTIVE);
        User user2 = new User("example3@gmail.com", "1234", UserStatus.ACTIVE);

        em.persist(user1);
        em.persist(user2);
        em.flush();
    }

    @Test
    @Rollback
    public void testCreateUser(){
        User user = userDAO.createUser("example2@gmail.com","12345");

        Assert.assertNotNull(user);
        Assert.assertEquals("example2@gmail.com", user.getEmail());
        Assert.assertEquals("12345", user.getPassword());
    }

    @Test
    @Rollback
    public void testGetUser(){
        setUpData();

        Optional<User> userOptional = userDAO.getUser("example@gmail.com");

        Assert.assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        Assert.assertEquals("example@gmail.com", user.getEmail());
        Assert.assertEquals("1234", user.getPassword());
    }

    @Test
    @Rollback
    public void testGetUserByEmailNotFound(){
        setUpData();

        Optional<User> userOptional = userDAO.getUser("example2@gmail.com");

        Assert.assertFalse(userOptional.isPresent());
    }

    @Test
    @Rollback
    public void testGetUserById(){
        User user = userDAO.createUser("example4@gmail.com","12345");

        Optional<User> userOptional = userDAO.getUser(user.getId());

        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals(user.getId(), userOptional.get().getId());
    }

    @Test
    @Rollback
    public void testDeleteUser(){
        setUpData();

        Optional<User> userOptional = userDAO.getUser("example3@gmail.com");
        User user = userOptional.get();
        Assert.assertEquals(UserStatus.ACTIVE , user.getStatus());

        userDAO.deleteUser(user);
        userOptional = userDAO.getUser("example3@gmail.com");
        user = userOptional.get();

        Assert.assertEquals(UserStatus.DELETED , user.getStatus());
    }
}
