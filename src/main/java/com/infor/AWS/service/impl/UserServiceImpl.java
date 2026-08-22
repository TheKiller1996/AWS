package com.infor.AWS.service.impl;

import com.infor.AWS.entity.AuthUser;
import com.infor.AWS.exception.ResourceNotFoundException;
import com.infor.AWS.repository.UserRepository;
import com.infor.AWS.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public AuthUser create(AuthUser authUser) {
        log.info("creating user with username {}", authUser.getEmail());
        Optional<AuthUser> userOptional = this.userRepository.findByEmail(authUser.getEmail());
        if(userOptional.isPresent()){
            throw new ResourceNotFoundException("User exists with current email");
        }
        return this.userRepository.save(authUser);
    }

    @Override
    public Optional<AuthUser> getById(UUID uuid) {
        log.info("Fetching user with id {}", uuid);
        return this.userRepository.findById(uuid);
    }

    @Override
    public void deleteById(UUID uuid) {
        log.info("Delete user with id {}", uuid);
        this.userRepository.deleteById(uuid);
    }

    @Override
    public AuthUser update(AuthUser authUser) {
        log.info("updating user with email {}", authUser.getEmail());
        return this.create(authUser);
    }
}
