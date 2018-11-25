package com.spm.erp.controller;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Login;
import com.spm.erp.security.JwtAuthenticationResponse;
import com.spm.erp.security.JwtTokenProvider;
import com.spm.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login login) {
        String jwt = userService.authenticateUser(login);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("/validateToken")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        CustomResponse response = userService.validateToken(tokenProvider.getJwtFromRequest(request));
        if (response.getSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut() {
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
