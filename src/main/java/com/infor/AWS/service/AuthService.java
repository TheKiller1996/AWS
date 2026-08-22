package com.infor.AWS.service;

import com.infor.AWS.dto.LoginReq;
import com.infor.AWS.dto.SignupRequest;

public interface AuthService {

    public String signup(SignupRequest signupRequest);
    public String login(LoginReq loginReq);
    public String refresh(String token);
    public String logout(String token);
}
