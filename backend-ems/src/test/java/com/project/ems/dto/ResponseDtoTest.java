package com.project.ems.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDtoTest {
    private ResponseDto responseDto;

    @BeforeEach
    public void setup() {
        responseDto = new ResponseDto();
    }

    @Test
    void getSessionId() {
        // Set sessionId
        responseDto.setSessionId("session");
        // Get sessionId
        String sessionId = responseDto.getSessionId();
        // compare
        Assertions.assertEquals("session", sessionId);
    }

    @Test
    void setSessionId() {
        // Set sessionId
        responseDto.setSessionId("session");
        // compare
        Assertions.assertEquals("session", responseDto.getSessionId());
    }
}