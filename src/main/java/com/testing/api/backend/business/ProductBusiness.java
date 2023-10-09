package com.testing.api.backend.business;


import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductBusiness {

    public String getProductById(String id)throws BaseException {
        System.out.println("Test");
        // TODO: get data from Database
        if (Objects.equals("1234", id)) {
            throw ProductException.idNotFound();
        }

        return id;
    }
}
