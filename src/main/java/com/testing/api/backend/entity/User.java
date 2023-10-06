package com.testing.api.backend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

//Table Name
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_User")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(nullable = false, length = 120)
    private String name;


}
