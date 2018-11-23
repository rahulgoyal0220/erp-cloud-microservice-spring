package com.spm.erp.service.impl;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Login;
import com.spm.erp.security.JwtTokenProvider;
import com.spm.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Override
    public CustomResponse validateToken(String token) {
        System.out.println(token);
        if (tokenProvider.validateToken(token)) {
            return new CustomResponse(true, "Success");
        } else {
            return new CustomResponse(false, "Unauthorized");
        }
    }

    @Override
    public String authenticateUser(Login login) {

        String email = customUserDetailsService.loadUserByUsername(login.getUsername());
        String jwt = tokenProvider.generateToken(email);

        return jwt;
    }
}
