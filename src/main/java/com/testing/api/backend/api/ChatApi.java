package com.testing.api.backend.api;

import com.testing.api.backend.business.ChatBusiness;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.model.ChatMessageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatApi {

    private final ChatBusiness business;

    public ChatApi(ChatBusiness chatBusiness) {
        this.business = chatBusiness;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> post(@RequestBody ChatMessageRequest request) throws BaseException {
        business.post(request);

        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
