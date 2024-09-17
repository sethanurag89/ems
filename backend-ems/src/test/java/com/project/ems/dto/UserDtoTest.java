package com.project.ems.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    private UserDto userDto;

    @BeforeEach
    public void setup() {
        userDto = new UserDto();
    }

    @Test
    void getEmail() {
        //Set email using setter
        userDto.setEmail("aseth@argusoft.com");
        //getter to retrieve the email
        String email = userDto.getEmail();
        //compare
        Assertions.assertEquals("aseth@argusoft.com", email);
    }

    @Test
    void setEmail() {
        //Set email using setter
        userDto.setEmail("aseth@argusoft.com");
        //compare
        Assertions.assertEquals("aseth@argusoft.com", userDto.getEmail());
    }

    @Test
    void getPassword() {
        //Set password using setter
        userDto.setPassword("password");
        //compare
        Assertions.assertEquals("password", userDto.getPassword());
    }

    @Test
    void setPassword() {
        //Set password using setter
        userDto.setPassword("password");
        //compare
        Assertions.assertEquals("password", userDto.getPassword());
    }
}