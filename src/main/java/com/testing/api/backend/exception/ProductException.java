package com.testing.api.backend.exception;

public class ProductException extends BaseException{

    public ProductException(String code){
        super("product."+ code);
    }

    public static ProductException idNull(){
        return new ProductException("Product.id.null");
    }

    public static ProductException idNotFound(){
        return new ProductException("Product.id.NotFound");
    }
}
