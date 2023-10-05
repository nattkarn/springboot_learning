package com.testing.api.backend.model;


import lombok.Data;

@Data
public class RegisterRequest {
    private String Email;
    private String password;
    private String name;
}
