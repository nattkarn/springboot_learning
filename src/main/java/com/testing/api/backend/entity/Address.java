package com.testing.api.backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

//Table Name
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_Address")
public class Address extends BaseEntity {



    @Column(length = 120)
    private String line1;

    @Column(length = 120)
    private String line2;

    @Column(length = 120)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "m_user_id",nullable = false)
    private User user;
}
