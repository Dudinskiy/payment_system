package com.example.payment_system.entity;

import com.example.payment_system.enums.UserRole;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Users extends BaseEntity {
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
    @Enumerated
    private UserRole userRole;
}
