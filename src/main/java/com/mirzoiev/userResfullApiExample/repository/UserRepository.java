package com.mirzoiev.userResfullApiExample.repository;

import com.mirzoiev.userResfullApiExample.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDTO class
 * data is stored in ArrayList
 *
 * @author R.M.
 * @since 14.09.2022
 */
@Repository
public class UserRepository {
    private static long userCountId;

    private final List<User> userList = new ArrayList<>(Arrays.asList(
            new User(++userCountId, "mail@.mail.com", "firstname1",
                    "lastname1", "2001-01-01",
                    "address1", "80977777777"),
            new User(++userCountId, "mail2@.mail.com", "firstname2",
                    "lastname2", "2002-02-02",
                    "address2", "80992222222"),
            new User(++userCountId, "mail3@.mail.com", "firstname3",
                    "lastname3", "2003-03-03",
                    "address3", "80933333333")
    ));

    public List<User> getUsersList() {
        return userList;
    }

    public User getUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findAny()
                .orElse(null);
    }

    public List<User> findUsersFromIntervalBirthDate(LocalDate dateFrom, LocalDate dateTo) {
        List<User> userListFromInterval = new ArrayList<>();
        List<LocalDate> dateListBetweenInterval = dateFrom.datesUntil(dateTo.plusDays(1))
                .collect(Collectors.toList());
        for (LocalDate localDate : dateListBetweenInterval) {
            User user = userList.stream()
                    .filter(user1 -> user1.getBirthDate().equals(localDate.toString()))
                    .findFirst()
                    .orElse(null);
            userListFromInterval.add(user);
        }
        userListFromInterval.removeAll(Collections.singleton(null));
        return userListFromInterval;
    }

    public void addUser(User user) {
        user.setId(++userCountId);
        this.userList.add(user);
    }

    public void updateUser(User user, String id) {
        int counter = 0;
        Long.parseLong(id);
        for (User user1 : userList) {
            if (user1.getId().equals(Long.parseLong(id))) {
                user.setId(Long.parseLong(id));
                userList.set(counter, user);
            }
            counter++;
        }
    }

    public void deleteUser(Long id) {
        userList.removeIf(user -> user.getId().equals(id));
    }
}
