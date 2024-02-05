package com.aquastilo.webapp.controller;

import com.aquastilo.webapp.controller.forms.user.CreateUserForm;
import com.aquastilo.webapp.controller.forms.user.PatchUserForm;
import com.aquastilo.webapp.dto.UserDto;
import com.aquastilo.webapp.interfaces.service.UserService;
import com.aquastilo.webapp.model.User;
import com.aquastilo.webapp.model.exceptions.UserAlreadyExistsException;
import com.aquastilo.webapp.model.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService us;


    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
        Long userId = Long.parseLong(id);
        final Optional<User> userOptional = us.getUser(userId);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(UserDto.fromUser(userOptional.get()));
        }
        else{
            throw new UserNotFoundException();
        }
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam("email") String email){
        final Optional<User> userOptional = us.getUser(email);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(UserDto.fromUser(userOptional.get()));
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserForm form){
        final User user = us.createUser(form.getEmail(), form.getPassword());
        if(user == null){
            throw new UserAlreadyExistsException();
        }
        return user;
    }

    @PatchMapping
    public User pacthUser(@RequestBody PatchUserForm form) {
        final User user = us.patchUser(form.getId(), form.getEmail(), form.getPassword());
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable String id){
        us.deleteUser(Long.parseLong(id));
    }
}
