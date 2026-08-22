package com.infor.AWS.controller;


import com.infor.AWS.config.ResponseBuilder;
import com.infor.AWS.dto.LoginReq;
import com.infor.AWS.dto.SignupRequest;
import com.infor.AWS.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request){
        String token = authService.signup(request);
        return ResponseBuilder.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq request){
        String token = authService.login(request);
        return ResponseBuilder.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody String token){
        authService.logout(token);
        return ResponseBuilder.ok("Logged out", null);
    }
}
