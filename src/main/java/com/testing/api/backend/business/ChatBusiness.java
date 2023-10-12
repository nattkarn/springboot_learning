package com.testing.api.backend.business;


import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.exception.ChatException;
import com.testing.api.backend.model.ChatMessage;
import com.testing.api.backend.model.ChatMessageRequest;
import com.testing.api.backend.util.SecurityUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ChatBusiness {

    private final SimpMessagingTemplate template;


    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }


    public void post(ChatMessageRequest request) throws BaseException {



        Optional<String> opt = SecurityUtil.getCurrentUserId();

        if (opt.isEmpty()){
            throw ChatException.accessDenied();
        }

        //TODO:validate message

        final String destination = "/topic/chat";
        ChatMessage payload = new ChatMessage();
        payload.setFrom(opt.get());
        payload.setMessage(request.getMessage());


        template.convertAndSend(destination,payload);

    }
}
