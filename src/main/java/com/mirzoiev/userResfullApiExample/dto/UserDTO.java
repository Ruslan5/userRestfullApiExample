package com.mirzoiev.userResfullApiExample.dto;

import com.mirzoiev.userResfullApiExample.entity.User;
import com.mirzoiev.userResfullApiExample.validations.DateValidator.UserDataValidator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UserDTO class
 *
 * @author R.M.
 * @since 14.09.2022
 */
@Validated
public class UserDTO {

    /**
     * users id is auto incremented field
     */
    private Long id;

    @Email(regexp = "^(.+)@(.+)$", message = "email is incorrect")
    @NotBlank(message = "email is incorrect")
    private String email;

    @NotNull
    @NotBlank
    private String firstname;
    @NotNull
    @NotBlank
    private String lastname;
    @NotNull(message = "birthDate is incorrect")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Past date exception")
    @UserDataValidator(message = "register users who are more than [18] years old")
    private Date birthDate;
    private String address;
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$", message = "phoneNumber is incorrect (0XX xxx xxxx)")
    private String phoneNumber;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(user.getId());
        user.setEmail(userDTO.getEmail());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        String formatDateString = new SimpleDateFormat("yyyy-MM-dd").format(userDTO.getBirthDate());
        user.setBirthDate(formatDateString);
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
}
