package com.project.ems.user;

import com.project.ems.entity.EmpDetails;
import com.project.ems.repositories.EmployeeRepository;
import com.project.ems.user.EmployeeInfoUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeInfoDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<EmpDetails> emp = employeeRepository.findByEmail(email);
        return emp.map(EmployeeInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found"+email));

    }
}
