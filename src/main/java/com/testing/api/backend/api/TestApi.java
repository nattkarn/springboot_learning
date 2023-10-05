package com.testing.api.backend.api;


import com.testing.api.backend.business.TestBusiness;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.exception.UserException;
import com.testing.api.backend.model.RegisterRequest;
import com.testing.api.backend.model.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestApi {

    //METHOD:1 Field Injection
    @Autowired
    private TestBusiness business;


    //METHOD:2 Constructor Injection
//    private final TestBusiness business;
//
//    public TestApi(TestBusiness business) {
//        this.business = business;
//    }


    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setName("ARM");
        response.setFood("KFC");
        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws BaseException {
        System.out.println("register");


        String response = business.register(request);
        return ResponseEntity.ok(response);


    }


}
