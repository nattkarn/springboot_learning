package com.testing.api.backend.exception;


public class UserException extends BaseException {
    public UserException(String code) {
        super("user." + code);

    }
    // user.register.email.null


    public static UserException notfound() {
        return new UserException("notfound");
    }

    public static UserException unauthorized() {
        return new UserException("unauthorized");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    public static UserException nameNull() {
        return new UserException("register.name.null");
    }

    public static UserException passwordtNull() {
        return new UserException("register.password.null");
    }


    //Create On DB

    public static UserException createemailNull() {
        return new UserException("create.email.null");
    }

    public static UserException createemailDuplicate() {
        return new UserException("create.email.duplicate");
    }

    public static UserException createrequestNull() {
        return new UserException("create.request.null");
    }

    public static UserException createnameNull() {
        return new UserException("create.name.null");
    }

    public static UserException createpasswordtNull() {
        return new UserException("create.password.null");
    }

    //update
    public static UserException updatenamenull() {
        return new UserException("update.name.null");
    }

    //Login
    public static UserException loginemailnotfound() {
        return new UserException("login.fail");
    }

    public static UserException loginpasswordincorrect() {
        return new UserException("login.fail");
    }

    public static UserException loginfailUserdeactivated() {
        return new UserException("login.fail");
    }

    //Activated
    public static UserException activateNoToken() {
        return new UserException("activate.token.null");
    }

    public static UserException activationFail() {
        return new UserException("activate.token.fail");
    }

    public static UserException activationTokenExpire() {
        return new UserException("activate.token.expire");
    }

    public static UserException activationAlready() {
        return new UserException("activate.token.already");
    }

    //Resend Activation Email
    public static UserException resendemailnotoken() {
        return new UserException("resend.email.token.null");
    }

    public static UserException resendactivationtokennotfound() {
        return new UserException("resend.email.token.notfound");
    }
}
