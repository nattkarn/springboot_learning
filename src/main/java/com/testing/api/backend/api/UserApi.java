package com.testing.api.backend.api;


import com.testing.api.backend.business.UserBusiness;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.model.LoginRequest;
import com.testing.api.backend.model.RegisterRequest;
import com.testing.api.backend.model.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws BaseException {
        String response = business.login(request);
        return ResponseEntity.ok(response);

    }


}
