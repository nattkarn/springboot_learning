package com.testing.api.backend.exception;

public class ChatException extends BaseException{
    public ChatException(String code) {
        super("Chat" + code);

    }
    // user.register.email.null


    public static ChatException accessDenied(){
        return new ChatException("access.denied");
    }
}
