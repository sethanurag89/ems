package com.project.ems.security;

import com.project.ems.user.EmployeeInfoDetailsService;
import com.project.ems.session.SessionFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private EmployeeInfoDetailsService employeeInfoDetailsService;
    @Autowired
    private SessionFilter sessionFilter;
    @Autowired
    private WebSecurityConfig webSecurityConfig;
    @Bean
    public UserDetailsService user(){
        return new EmployeeInfoDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http = http.exceptionHandling().authenticationEntryPoint(
                (request,response,ex)->{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage());
                }
        ).and();
        http.securityMatcher("/employees/**","/contact/**","/address/**").authorizeHttpRequests(
                authorize->authorize.anyRequest().authenticated()
        ).httpBasic(withDefaults()).formLogin();
        http.addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(user());
        authenticationProvider.setPasswordEncoder(webSecurityConfig.passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeInfoDetailsService).passwordEncoder(webSecurityConfig.passwordEncoder());
    }
}
