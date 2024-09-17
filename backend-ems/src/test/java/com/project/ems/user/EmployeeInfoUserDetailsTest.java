package com.project.ems.user;

import com.project.ems.entity.EmpDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeInfoUserDetailsTest {
    private EmployeeInfoUserDetails userDetails;

    @BeforeEach
    public void setup() {
        EmpDetails empDetails = new EmpDetails();
        empDetails.setEmail("aseth@argusoft.com");
        empDetails.setPassword("password");
        empDetails.setActive(true);
        empDetails.setRole("ROLE_ADMIN");

        userDetails = new EmployeeInfoUserDetails(empDetails);
    }

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        List<GrantedAuthority> expectedAuthorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Assertions.assertEquals(expectedAuthorities, authorities);
    }

    @Test
    void getPassword() {
        String password = userDetails.getPassword();
        Assertions.assertEquals("password", password);
    }

    @Test
    void getUsername() {
        String username = userDetails.getUsername();
        Assertions.assertEquals("aseth@argusoft.com", username);
    }

    @Test
    void isAccountNonExpired() {
        boolean accountNonExpired = userDetails.isAccountNonExpired();
        Assertions.assertTrue(accountNonExpired);
    }

    @Test
    void isAccountNonLocked() {
        boolean accountNonLocked = userDetails.isAccountNonLocked();
        Assertions.assertTrue(accountNonLocked);
    }

    @Test
    void isCredentialsNonExpired() {
        boolean credentialsNonExpired = userDetails.isCredentialsNonExpired();
        Assertions.assertTrue(credentialsNonExpired);
    }

    @Test
    void isEnabled() {
        boolean enabled = userDetails.isEnabled();
        Assertions.assertTrue(enabled);
    }
}