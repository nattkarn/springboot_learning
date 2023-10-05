package com.testing.api.backend.business;


import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.exception.UserException;
import com.testing.api.backend.model.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TestBusiness {


    public String register(RegisterRequest request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }


        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        } else if (Objects.isNull(request.getPassword())) {
            throw UserException.passwordtNull();
        } else if (Objects.isNull(request.getName())) {
            throw UserException.nameNull();
        }


        return "Request OK";
    }


}
