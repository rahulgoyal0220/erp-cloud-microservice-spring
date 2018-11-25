package com.spm.erp.service.impl;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Employee;
import com.spm.erp.model.Login;
import com.spm.erp.repository.EmployeeRepository;
import com.spm.erp.security.JwtTokenProvider;
import com.spm.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public CustomResponse validateToken(String token) {
        System.out.println(token);
        if (tokenProvider.validateToken(token)) {
            String email = tokenProvider.getUserEmailFromJWT(token);
            Employee employee = employeeRepository.findByEmail(email).get();

            List<String> roles = employee.getRoles().stream().map(x -> x.getName().toString()).collect(Collectors.toList());
            return new CustomResponse(true, "Success", roles);
        } else {
            return new CustomResponse(false, "Unauthenticated", null);
        }
    }

    @Override
    public String authenticateUser(Login login) {

        String email = customUserDetailsService.loadUserByUsername(login.getUsername());
        String jwt = tokenProvider.generateToken(email);

        return jwt;
    }
}
