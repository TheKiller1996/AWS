package com.infor.AWS.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "entries")
@Data
@EqualsAndHashCode(callSuper = true)
public class Entry extends BasicEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id",nullable = false, name = "user_id")
    private AuthUser user;
}
