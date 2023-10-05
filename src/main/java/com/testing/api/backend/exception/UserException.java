package com.testing.api.backend.exception;


public class UserException extends BaseException {
    public UserException(String code) {
        super("User" + code);

    }
    // user.register.email.null
    public static UserException emailNull(){
        return new UserException("Register.email.null");
    }

    public static UserException requestNull(){
        return new UserException("Register.request.null");
    }

    public static UserException nameNull(){
        return new UserException("Register.request.null");
    }

    public static UserException passwordtNull(){
        return new UserException("register.request.null");
    }
}
