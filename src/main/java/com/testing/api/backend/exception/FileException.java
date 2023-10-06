package com.testing.api.backend.exception;


public class FileException extends BaseException {
    public FileException(String code) {
        super("File" + code);

    }
    // user.register.email.null
    public static FileException fileNull(){
        return new FileException("File.null");
    }

    public static FileException fileMaxSize(){
        return new FileException("File.maxSize");
    }

    public static FileException fileUnsupport(){
        return new FileException("File.unsupportType");
    }
}
