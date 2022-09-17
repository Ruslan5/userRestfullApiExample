package com.mirzoiev.userResfullApiExample.service;

import com.mirzoiev.userResfullApiExample.entity.User;
import com.mirzoiev.userResfullApiExample.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setup(){
        user =  User.builder()
                .id(1l)
                .firstname("testfNamOne")
                .lastname("testlNamOne")
                .email("testOne@mail.com")
                .birthDate("2004-07-20")
                .address("test address")
                .phoneNumber("8097 777 7777")
                .build();
    }

    @DisplayName("JUnit test for testGetAllUsers method")
    @Test
    void testGetAllUsers() {
        User user1 =  User.builder()
                .id(1l)
                .firstname("testfNam2")
                .lastname("testlNam2")
                .email("testOne@mail.com")
                .birthDate("2002-07-20")
                .address("test address2")
                .phoneNumber("8097 777 7772")
                .build();
        given(userRepository.getUsersList()).willReturn(List.of(user, user1));

        List<User> userList = userService.getAllUsers();
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    void getUserById() {
        given(userRepository.getUserById(1l)).willReturn(user);
        User savedUser = userService.getUserById(user.getId());

        assertThat(savedUser).isNotNull();

    }

    @Test
    void createUser() {
        userService.createUser(user);
        assertThat(user).isNotNull();
    }

    @Test
    void updateUser() {
        String updatedFirstName = "testUpdatedName";
        userRepository.addUser(user);
       user.setFirstname(updatedFirstName);
       userService.updateUser(user, "1");
       assertThat(user.getFirstname()).isEqualTo(updatedFirstName);
    }

    @Test
    void deleteUser() {
        long userId = 1l;
        willDoNothing().given(userRepository).deleteUser(userId);

        userService.deleteUser(userId);

        verify(userRepository, timeout(1)).deleteUser(userId);
    }
}