package com.testing.api.backend.api;


import com.testing.api.backend.business.ProductBusiness;
import com.testing.api.backend.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductApi {


    @Autowired
    private ProductBusiness business;
    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws BaseException {


        String response = business.getProductById(id);
        return ResponseEntity.ok(response);
    }
}
