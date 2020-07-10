package com.thecodinginterface.todobackend.controllers;

import com.thecodinginterface.todobackend.models.User;
import com.thecodinginterface.todobackend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable final Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user) {
        var existingUser = userRepository.findById(id).orElseGet(null);
        BeanUtils.copyProperties(user, existingUser, "id");
        return userRepository.save(existingUser);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
