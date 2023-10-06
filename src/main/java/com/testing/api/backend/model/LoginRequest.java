package com.testing.api.backend.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String Email;
    private String password;
}
