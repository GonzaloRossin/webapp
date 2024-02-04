package com.aquastilo.webapp.persistence;

import com.aquastilo.webapp.interfaces.persistence.UserDAO;
import com.aquastilo.webapp.model.User;
import com.aquastilo.webapp.model.enums.status.UserStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UserJpaDao implements UserDAO {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    @Override
    public User createUser(final String email, final String password) {
        final User user = new User(email, password, UserStatus.ACTIVE);
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> getUser(long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> getUser(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        try {
            User user = query.getSingleResult();
            return Optional.ofNullable(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public User patchUser(User user, String email, String password) {
        if (email != null) {
            user.setEmail(email);
        }
        if (password != null) {
            user.setPassword(password);
        }
        em.merge(user);
        return user;
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
            user.setStatus(UserStatus.DELETED);
            em.merge(user);
    }
}
