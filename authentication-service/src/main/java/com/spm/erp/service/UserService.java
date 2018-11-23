package com.spm.erp.service;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Login;

public interface UserService {

    String authenticateUser(Login login);

    CustomResponse validateToken(String token);
}
