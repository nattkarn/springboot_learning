package com.testing.api.backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//Table Name
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_User")
public class User extends BaseEntity implements Serializable {

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(nullable = false, length = 120)
    private String name;

    private String CitizenId;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Social social;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Address> addresses;

    private String token;

    private Date tokenExpire;

    private boolean activated;
}
