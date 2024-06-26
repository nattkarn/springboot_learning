package com.testing.api.backend;

import com.testing.api.backend.business.EmailBusiness;
import com.testing.api.backend.exception.BaseException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestEmailBusiness {

    @Autowired
    private EmailBusiness emailBusiness;

    @Order(1)
    @Test
    void testSendActivateEmail() throws BaseException {
        emailBusiness.senActivateUserEmail(
                TestData.email,
                TestData.name,
                TestData.token
        );
    }


    interface TestData {
        String email = "armnattkan@gmail.com";
        String name = "Nattkarn Prajansri";
        String token = "dawdoajdklsaihfgioadkawodkalw";
    }
}
