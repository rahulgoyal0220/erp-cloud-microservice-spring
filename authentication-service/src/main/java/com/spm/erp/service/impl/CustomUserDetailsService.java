package com.spm.erp.service.impl;

import com.spm.erp.model.Employee;
import com.spm.erp.repository.EmployeeRepository;
import com.spm.erp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    public String loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User with email : " + email + " not found!."));
        return user.getEmail();
    }
}
