package com.mirzoiev.userResfullApiExample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirzoiev.userResfullApiExample.dto.UserDTO;
import com.mirzoiev.userResfullApiExample.UserResfullApiExample;
import com.mirzoiev.userResfullApiExample.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserResfullApiExample.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    void getAllUsers() throws Exception {
        User user = getCreatedUser();
        List<User> userList = new ArrayList<>();
        userList.add(user);

        given(userController.getAllUsers()).willReturn(userList);
        mvc.perform(get("/users").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].id", is(user.getId())));
    }

    @Test
    void findUserById() throws Exception {
        mvc.perform(get("/users/1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findUserByBirthDate() throws Exception {
        mvc.perform(get("/user?from=2000-01-01&to=2022-01-01").contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception {
        UserDTO userDTO = getCreatedUserDTO();
        doNothing().when(userController).createUser(userDTO);
        mvc.perform(post("/users").content(asJson(userDTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void updateUser() throws Exception {
        UserDTO userDTO = getCreatedUserDTO();
        doNothing().when(userController).updateUser(userDTO, String.valueOf(1l));
        mvc.perform(put("/users/" + userDTO.getId())
                        .content(asJson(userDTO))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void deleteUser() throws Exception {
        UserDTO userDTO = getCreatedUserDTO();
        doNothing().when(userController).deleteUser(1l);
        mvc.perform(delete("/users/" + userDTO.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
    private User getCreatedUser() {
        User user = new User();
//        user.setId(1l);
        user.setFirstname("testfNamOne");
        user.setLastname("testlNamOne");
        user.setEmail("testOne@mail.com");
        user.setBirthDate("2003-07-20");
        user.setAddress("test address");
        user.setPhoneNumber("8097 777 7777");
        return user;
    }

    private UserDTO getCreatedUserDTO() throws ParseException {
        UserDTO user = new UserDTO();
        user.setFirstname("testfNamOne");
        user.setLastname("testlNamOne");
        user.setEmail("testOne@mail.com");
        String dateStr = "2000-11-23";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse(dateStr);
        user.setBirthDate(date);
        user.setAddress("test address");
        user.setPhoneNumber("097 777 7777");
        return user;
    }
    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}