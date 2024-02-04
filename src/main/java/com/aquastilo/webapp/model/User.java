package com.aquastilo.webapp.model;

import com.aquastilo.webapp.model.enums.status.UserStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(updatable = false)
    private Long id;
    @Column(length = 50,
            unique = true,
            nullable = false
    )
    private String email;
    @Column(length = 30,
            nullable = false
    )
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    public User( String email, String password, UserStatus status) {
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public User( Long id, String email, String password, UserStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public User() {
        /*just for hibernate*/
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
