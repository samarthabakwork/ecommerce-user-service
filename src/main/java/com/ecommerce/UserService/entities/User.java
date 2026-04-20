package com.ecommerce.UserService.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Enumeration;

@Entity
@Table(name="ecommerce_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = false,unique = true,length = 20)
    private String email;

    @Column(nullable = false,length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}

