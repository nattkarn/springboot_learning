package com.testing.api.backend.exception;


public class EmailException extends BaseException {
    public EmailException(String code) {
        super("email" + code);

    }
    // user.register.email.null
    public static EmailException templateNotFound(){
        return new EmailException("email.templateNotFound");
    }
}


