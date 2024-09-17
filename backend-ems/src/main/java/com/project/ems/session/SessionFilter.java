package com.project.ems.session;

import com.project.ems.user.EmployeeInfoDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SessionFilter extends OncePerRequestFilter {
    @Autowired
    private InMemorySessionRegistry sessionRegistry;
    @Autowired
    private EmployeeInfoDetailsService employeeInfoDetailsService;

    public SessionFilter(InMemorySessionRegistry inMemorySessionRegistry,
                         EmployeeInfoDetailsService theEmployeeInfoDetailsService){
        sessionRegistry = inMemorySessionRegistry;
        employeeInfoDetailsService = theEmployeeInfoDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(sessionId==null || sessionId.length()==0){
            filterChain.doFilter(request, response);
            return;
        }
        String email = sessionRegistry.getEmailForSession(sessionId);
        if(email==null || email.length()==0){
            filterChain.doFilter(request, response);
            return;
        }
        final UserDetails currentEmployee = employeeInfoDetailsService.loadUserByUsername(email);
        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                currentEmployee,
                null,
                currentEmployee.getAuthorities()
                );
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}
