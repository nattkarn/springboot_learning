package com.testing.api.backend.business;


import com.testing.api.backend.entity.User;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.exception.FileException;
import com.testing.api.backend.exception.UserException;
import com.testing.api.backend.mapper.UserMapper;
import com.testing.api.backend.model.*;
import com.testing.api.backend.service.TokenService;
import com.testing.api.backend.service.UserService;
import com.testing.api.backend.util.SecurityUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Calendar;

@Service
@Log4j2
public class UserBusiness {


    private final UserService userService;

    private final UserMapper userMapper;

    private final TokenService tokenService;


    private final EmailBusiness emailBusiness;


    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService, EmailBusiness emailBusiness) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.emailBusiness = emailBusiness;
    }

    public RegisterResponse register(RegisterRequest request) throws BaseException {
        String token = SecurityUtil.generateToken();
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName(), token, nextXMinute(30));
        sendEmail(user);
        return userMapper.toRegisterResponse(user);
    }


    public ActivateResponse activate(ActivateRequest request) throws BaseException {
        String token = request.getToken();
        if (StringUtil.isNullOrEmpty(token)) {
            throw UserException.activateNoToken();
        }

        Optional<User> opt = userService.findByToken(token);
        if (opt.isEmpty()) {
            throw UserException.activationFail();
        }
        User user = opt.get();

        if (user.isActivated()){
            throw UserException.activationAlready();
        }


        Date now = new Date();
        Date expireDate = user.getTokenExpire();
        if (now.after(expireDate)) {
            //TODO: re-email

            throw UserException.activationTokenExpire();

        }

        user.setActivated(true);
        userService.update(user);
        ActivateResponse response = new ActivateResponse();
        response.setSuccess(true);
        return response;

    }

    public void resendActivationEmail(ResendActivationEmailRequest request) throws BaseException {
        String email = request.getEmail();
        if (StringUtil.isNullOrEmpty(email)){
            throw UserException.resendemailnoemail();
        }
        Optional<User> opt = userService.findByEmal(email);
        if (opt.isEmpty()){
            throw UserException.resendactivationfail();
        }
        User user = opt.get();

        if(user.isActivated()){
            throw UserException.activationAlready();
        }

        user.setToken(SecurityUtil.generateToken());
        user.setTokenExpire(nextXMinute(30));
        user = userService.update(user);
        sendEmail(user);

    }

    private Date nextXMinute(int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    private void sendEmail(User user) {

        String token = user.getToken();
        try {
            emailBusiness.senActivateUserEmail(user.getEmail(), user.getName(), token);
        } catch (BaseException e) {
            throw new RuntimeException(e);
        }


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

    public LoginResponse login(LoginRequest request) throws BaseException {

        Optional<User> opt = userService.findByEmal(request.getEmail());
        if (opt.isEmpty()) {
            throw UserException.loginemailnotfound();
        }

        //Verify password
        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            //throw login fail, password incorrect
            throw UserException.loginpasswordincorrect();
        }

        //TODO: Verify Activate Status
        if (!user.isActivated()) {
            throw UserException.loginfailUserdeactivated();
        }


        LoginResponse response = new LoginResponse();
        response.setToken(tokenService.tokenize(user));
        return response;
    }

    public String refreshToken() throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }
        String userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.notfound();
        }

        User user = optUser.get();
        return tokenService.tokenize(user);
    }

}
