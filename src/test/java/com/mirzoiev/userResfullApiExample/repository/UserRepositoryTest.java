package com.mirzoiev.userResfullApiExample.repository;

import com.mirzoiev.userResfullApiExample.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void testGetUsersList() {
        int expectedSizeOfUsers = 3;
        List<User> userList = userRepository.getUsersList();
        log.debug(userList.toString());
        assertEquals(expectedSizeOfUsers, userList.size());
    }

    @Test
    void getUserById() {
        long findUserId = 1l;
        User foundUser = userRepository.getUserById(findUserId);
        assertEquals(findUserId, foundUser.getId());
    }

    @Test
    void findUsersFromIntervalBirthDate() {
        LocalDate dateFrom = LocalDate.of(2000, 01, 01);
        LocalDate dateTo = LocalDate.of(2002, 03, 01);
        int expectedSizeOfUsers = 2;
        List<User> userList = userRepository.findUsersFromIntervalBirthDate(dateFrom, dateTo);
        assertEquals(expectedSizeOfUsers, userList.size());
    }

    @Test
    void addUser() {
        int expectedSizeOfUsers = 4;
        List<User> userList = new ArrayList<>();
        User user = getCreatedUser();
        userRepository.addUser(user);
        userRepository.getUsersList()
                .forEach(u -> userList.add(u));
        log.debug(userList.toString());
        assertEquals(expectedSizeOfUsers, userList.size());
        userRepository.deleteUser(user.getId());
    }

    @Test
    void updateUser() {
        String updatedFirstName = "new Name";
        long userId = 2l;
        User user = userRepository.getUserById(userId);
        user.setFirstname(updatedFirstName);
        userRepository.updateUser(user, String.valueOf(userId));
        assertEquals(updatedFirstName, user.getFirstname());
    }

    @Test
    void deleteUser() {
        int expectedUserListSizeAfterDelete = 3;
        User user = getDeletedUser();
        userRepository.addUser(user);
        userRepository.deleteUser(user.getId());
        List<User> afterDelete = userRepository.getUsersList();
        assertEquals(afterDelete.size(), expectedUserListSizeAfterDelete);
    }

    private User getCreatedUser() {
        User user = new User();
        user.setFirstname("testfNamOne");
        user.setLastname("testlNamOne");
        user.setEmail("testOne@mail.com");
        user.setBirthDate("2003-07-20");
        user.setAddress("test address");
        user.setPhoneNumber("8097 777 7777");
        return user;
    }
    private User getDeletedUser() {
        User user = new User();
        user.setFirstname("deleted user");
        user.setLastname("testlNamOne");
        user.setEmail("testOne@mail.com");
        user.setBirthDate("2003-07-20");
        user.setAddress("test address");
        user.setPhoneNumber("8097 777 7777");
        return user;
    }
}