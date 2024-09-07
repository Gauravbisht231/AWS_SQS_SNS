package com.example.aws.contollers;

import com.example.aws.dto.PubMessageDto;
import com.example.aws.services.snsService.PublishMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PublishMessageController {
    @Autowired
    private PublishMessage publishMessage;
    @PostMapping("/createtopic/message")
    public void TopicMessage(@RequestBody PubMessageDto pubMessageDto){
        this.publishMessage.pubTopic(pubMessageDto);
    }
}
