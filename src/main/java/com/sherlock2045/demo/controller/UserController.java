package com.sherlock2045.demo.controller;

import com.sherlock2045.demo.model.User;
import com.sherlock2045.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    private User getBooks(@PathVariable("id") int id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }

    }

    @DeleteMapping("/user/{id}")
    private void deleteBook(@PathVariable("id") int id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @PostMapping("/user")
    private User saveBook(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/user/{id}")
    private User update(@PathVariable("id") int id, @RequestBody @Valid User user) {

        return userService.update(user, id);
    }
}
