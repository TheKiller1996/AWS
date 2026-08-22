package com.infor.AWS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Data
public class BasicEntity {


    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "created_at", nullable = false)
    @Setter(AccessLevel.NONE)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt = Instant.now();

    @Column(name = "modified_at", nullable = false)
    private Instant modifiedAt = Instant.now();
}
