package com.project.ems.controllers;

import com.project.ems.dto.ResponseDto;
import com.project.ems.dto.UserDto;
import com.project.ems.session.InMemorySessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ems")
public class LoginController {
    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public AuthenticationProvider authenticationProvider;
    @Autowired
    public InMemorySessionRegistry sessionRegistry;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserDto user){
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        final String sessionID = sessionRegistry.registerSession(user.getEmail());
        ResponseDto response = new ResponseDto();
        response.setSessionId(sessionID);
        return ResponseEntity.ok(response);
    }

}
