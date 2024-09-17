package com.project.ems.session;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemorySessionRegistryTest {
    private InMemorySessionRegistry sessionRegistry;

    @BeforeEach
    public void setup() {
        sessionRegistry = new InMemorySessionRegistry();
    }

    @Test
    public void testRegisterSession() {
        String email = "aseth@argusoft.com";
        String sessionId = sessionRegistry.registerSession(email);
        String retrievedEmail = sessionRegistry.getEmailForSession(sessionId);
        Assertions.assertNotNull(sessionId);
        Assertions.assertNotNull(retrievedEmail);
        Assertions.assertEquals(email, retrievedEmail);

    }
}