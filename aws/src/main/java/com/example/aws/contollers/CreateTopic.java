package com.example.aws.contollers;

import com.example.aws.services.snsService.CreateSns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CreateTopic {
    @Autowired
    private CreateSns createSnsTopic;
    @PostMapping("/createtopic")
    public String createtopic(@RequestBody String topic){
        return this.createSnsTopic.createSnsTopic(topic);
    }
}
