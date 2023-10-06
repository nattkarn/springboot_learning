package com.testing.api.backend.business;


import com.testing.api.backend.entity.User;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.exception.FileException;
import com.testing.api.backend.exception.UserException;
import com.testing.api.backend.mapper.UserMapper;
import com.testing.api.backend.model.LoginRequest;
import com.testing.api.backend.model.RegisterRequest;
import com.testing.api.backend.model.RegisterResponse;
import com.testing.api.backend.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {


    private final UserService userService;

    private final UserMapper userMapper;


    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public RegisterResponse register(RegisterRequest request) throws BaseException {

        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
        return userMapper.toRegisterResponse(user);
    }


    public String uploadprofilePicture(MultipartFile file) throws BaseException {
        if (file == null) {
            //thow error
            throw FileException.fileNull();
        }

        //if (file.getSize() > convertSizeToBytes(maxFileSize))
        if (file.getSize() > 1048576 * 10) {
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            //throw error
            throw FileException.fileUnsupport();
        }

        List<String> supportedType = Arrays.asList("image/jpeg", "image/png");
        if (!supportedType.contains(contentType)) {
            //throw error unsopport
            throw FileException.fileUnsupport();
        }


        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public String login(LoginRequest request) throws BaseException {

        Optional<User> opt = userService.findByEmal(request.getEmail());
        if (opt.isEmpty()){
            throw UserException.loginemailnotfound();
        }


        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())){
            //throw login fail, password incorrect
            throw UserException.loginpasswordincorrect();
        }
        //TODO: JWT(Json Web Token)
        String token = "JWT(Json Web Token)";
        return "";
    }

}
