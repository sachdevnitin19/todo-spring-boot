package com.nitin.todo.controller;

import com.nitin.todo.model.User;
import com.nitin.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        System.out.println(user);
        this.userService.createNewUser(user);
        return new ResponseEntity("Ok", new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers() {
        return new ResponseEntity(this.userService.getUsers(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public User findUserById(@PathVariable("userId") UUID userId) {
        return this.userService.findUserById(userId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable("id") UUID id) {
        this.userService.deleteUserById(id);
        return new ResponseEntity("", new HttpHeaders(), HttpStatus.OK);
    }
}
