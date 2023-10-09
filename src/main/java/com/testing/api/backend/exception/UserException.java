package com.testing.api.backend.exception;


public class UserException extends BaseException {
    public UserException(String code) {
        super("User" + code);

    }
    // user.register.email.null


    public static UserException notfound(){
        return new UserException("notfound");
    }

    public static UserException unauthorized(){
        return new UserException("unauthorized");
    }
    public static UserException emailNull(){
        return new UserException("Register.email.null");
    }

    public static UserException requestNull(){
        return new UserException("Register.request.null");
    }

    public static UserException nameNull(){
        return new UserException("Register.name.null");
    }

    public static UserException passwordtNull(){
        return new UserException("register.password.null");
    }


    //Create On DB

    public static UserException createemailNull(){
        return new UserException("create.email.null");
    }
    public static UserException createemailDuplicate(){
        return new UserException("create.email.duplicate");
    }

    public static UserException createrequestNull(){
        return new UserException("create.request.null");
    }

    public static UserException createnameNull(){
        return new UserException("create.name.null");
    }

    public static UserException createpasswordtNull(){
        return new UserException("create.password.null");
    }

    //Login
    public static UserException loginemailnotfound(){
        return new UserException("login.fail");
    }
    public static UserException loginpasswordincorrect(){
        return new UserException("login.fail");
    }



}
