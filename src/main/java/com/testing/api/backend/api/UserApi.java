package com.testing.api.backend.api;


import com.testing.api.backend.business.UserBusiness;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserApi {

    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }


    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) throws BaseException {
        RegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);


    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadprofilePicture(file);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException {
        String response = business.refreshToken();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws BaseException {
        LoginResponse response = business.login(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/activate")
    public ResponseEntity<ActivateResponse> activate (@RequestBody ActivateRequest request) throws BaseException {
        ActivateResponse reponse = business.activate(request);
        return ResponseEntity.ok(reponse);
    }

    @PostMapping("/resend-avtivation-email")
    public ResponseEntity<Void> resendActivationEmail (@RequestBody ResendActivationEmailRequest request) throws BaseException {
        business.resendActivationEmail(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
