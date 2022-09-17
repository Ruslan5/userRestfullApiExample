package com.mirzoiev.userResfullApiExample.service;

import com.mirzoiev.userResfullApiExample.entity.User;
import com.mirzoiev.userResfullApiExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * class UserServiceImpl
 * implements interface UserService
 * <p>
 * Service layer implemented business logic
 *
 * @author R.M.
 * @see com.mirzoiev.userResfullApiExample.service.UserService
 * @since 14.09.2022
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getUsersList();
    }

    @Override
    public User getUserById(Long id) {
        if (userRepository.getUserById(id) == null) {
            throw new IllegalArgumentException();
        }
        return userRepository.getUserById(id);
    }

    @Override
    public void createUser(User user) {
        this.userRepository.addUser(user);
    }

    @Override
    public void updateUser(User user, String id) {
        this.userRepository.updateUser(user, id);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteUser(id);
    }

    @Override
    public List<User> findUsersFromIntervalBirthDate(String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateFromStr = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToStr = LocalDate.parse(dateTo, formatter);
        if (dateToStr.isBefore(dateFromStr)) {
            throw new IllegalArgumentException();
        }
        return userRepository.findUsersFromIntervalBirthDate(dateFromStr, dateToStr);
    }

}
