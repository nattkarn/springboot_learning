package com.testing.api.backend.business;

import com.testing.api.backend.exception.BaseException;

import com.testing.api.backend.service.EmailRequest;
import com.testing.api.backend.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
public class EmailBusiness {


    private final KafkaTemplate<String, EmailRequest> kafkaEmailTemplate;

    private final EmailService emailService;

    public EmailBusiness(KafkaTemplate<String, EmailRequest> kafkaEmailTemplate, EmailService emailService) {

        this.kafkaEmailTemplate = kafkaEmailTemplate;
        this.emailService = emailService;
    }



    public void senActivateUserEmail(String email, String name, String token) throws BaseException {
        //prepare content html
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Welcome to Backend Service</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Hello , ${P_NAME}</h1>\n" +
                "<h2>Please activate your account</h2>\n" +
                "<p>Click <a href=\"${P_LINK}\">here</a></p>\n" +
                "</body>\n" +
                "</html>";

//        try {
////            html = readEmailTemplate("email-activate-user.html");
//
//        } catch (IOException e) {
//            throw EmailException.templateNotFound();
//        }
        log.info("Token: " + token);
        String finallink = "http://192.168.1.163:3000/activate/" + token;
        html = html.replace("${P_NAME}", name);
        html = html.replace("${P_LINK}", finallink);


        String subject = "Please activate your account";


        EmailRequest request = new EmailRequest();
        request.setTo(email);
        request.setSubject(subject);
        request.setContent(html);


        //prepare Subject

        emailService.send(request.getTo(), request.getSubject(), request.getContent());
        log.info("Email Send");
//        CompletableFuture<SendResult<String, EmailRequest>> future = kafkaEmailTemplate.send("activation-email", request);
//        future.thenAccept(result -> {
//            // onSuccess
//            log.info("kafka send success");
//            log.info(result.toString());
//        }).exceptionally(ex -> {
//            // onFailure
//            log.error("kafka send fail", ex);
//            return null;
//        });


    }



    private String readEmailTemplate(String filename) throws IOException {
        File file = ResourceUtils.getFile("classpath:email/" + filename);
        return FileCopyUtils.copyToString(new FileReader(file));
    }
}
