package com.example.payment_system.entity;

import com.example.payment_system.enums.UserRole;
import com.example.payment_system.enums.UserStatus;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "users")
public class UsersEntity extends BaseEntity {
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    @ToString.Exclude
    private String userPassword;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
}
