package com.infor.AWS.repository;

import com.infor.AWS.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, UUID> {
    public Optional<AuthUser> findByEmail(String email);
}
