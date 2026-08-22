package com.infor.AWS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "auth_users")
@Data
public class AuthUser extends BasicEntity{

    private String email;

    private String password;

}
