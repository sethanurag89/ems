package com.project.ems.session;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Component
public class InMemorySessionRegistry {
    private static final HashMap<String, String> sessions = new HashMap<>();

    public String registerSession(final String email){
        if(email==null){
            throw new RuntimeException("email is null");
        }
        final String sessionId = generateSessionID();
        sessions.put(sessionId, email);
        return sessionId;
    }

    public String getEmailForSession(final String sessionId){
        return sessions.get(sessionId);
    }

    private String generateSessionID(){
        return new String(
                Base64.getEncoder().encode(
                        UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
                )
        );
    }
}
