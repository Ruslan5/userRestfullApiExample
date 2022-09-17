package com.mirzoiev.userResfullApiExample.service;

import com.mirzoiev.userResfullApiExample.entity.User;

import java.util.List;

/**
 * interface UserService
 *
 * @author R.M.
 * @since 14.09.2022
 */
public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);
    void createUser(User user);

    void updateUser(User user, String id);

    void deleteUser(Long id);

    List<User> findUsersFromIntervalBirthDate(String dateFrom, String dateTo);


}
