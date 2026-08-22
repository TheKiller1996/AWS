package com.infor.AWS.service.impl;

import com.infor.AWS.dto.LoginReq;
import com.infor.AWS.dto.SignupRequest;
import com.infor.AWS.entity.AuthUser;
import com.infor.AWS.exception.DuplicateResourceException;
import com.infor.AWS.exception.InvalidCredentialsException;
import com.infor.AWS.exception.ResourceNotFoundException;
import com.infor.AWS.repository.UserRepository;
import com.infor.AWS.service.AuthService;
import com.infor.AWS.service.UserService;
import com.infor.AWS.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String signup(SignupRequest signupRequest) {
        Optional<AuthUser> user = userRepository.findByEmail(signupRequest.getEmail());
        if(user.isPresent()){
            log.warn("Signup attempted with existing email {}", signupRequest.getEmail());
            throw new DuplicateResourceException("User with email already exists");
        }
        AuthUser authUser = new AuthUser();
        authUser.setEmail(signupRequest.getEmail());
        authUser.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));

        AuthUser createdUser = userService.create(authUser);
        log.info("User signed up successfully with email {}", signupRequest.getEmail());
        return jwtUtil.generateToken(createdUser.getId(), createdUser.getEmail());
    }

    @Override
    public String login(LoginReq loginReq) {
        String email = loginReq.getEmail();
        Optional<AuthUser> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            log.warn("User not found with email {}",email);
            throw new ResourceNotFoundException("User with provided email and password does not exist");
        }
        if(!bCryptPasswordEncoder.matches(loginReq.getPassword(), user.get().getPassword())){
            log.warn("Invalid credentials for user with email {}", email);
            throw new InvalidCredentialsException("Username and Password combination is invalid");
        }
        return jwtUtil.generateToken(user.get().getId(), user.get().getEmail());
    }

    @Override
    public String refresh(String token) {
        return "";
    }

    @Override
    public String logout(String token) {
        UUID userId = jwtUtil.validateAndGetUserId(token);
        log.info("Logging out user with is {}", userId);
        return "";
    }
}
