package com.mirzoiev.userResfullApiExample.controller;

import com.mirzoiev.userResfullApiExample.dto.UserDTO;
import com.mirzoiev.userResfullApiExample.entity.User;
import com.mirzoiev.userResfullApiExample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller class for column
 *
 * @author R.M.
 * @since 14.09.2022
 */
@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public User findUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(Long.parseLong(id));
        return user;
    }

    /**
     * http://localhost:8080/user?from=01-01-2020&to=01-02-2021
     *
     * @param from
     * @param to
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public List<User> findUserByBirthDate(String from, String to) {
        return userService.findUsersFromIntervalBirthDate(from, to);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void createUser(@Valid @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO.dtoToUser(userDTO));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public void updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") String id) {
        userService.updateUser(userDTO.dtoToUser(userDTO), id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public String handle(IllegalArgumentException e) {
        log.debug(e.getMessage());
        return "user not found";
    }

}
