package com.demo.authservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String imageUrl;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

}
